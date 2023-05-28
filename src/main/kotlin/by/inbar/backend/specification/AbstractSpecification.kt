package by.inbar.backend.specification

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.dto.filter.FilterMetadata
import by.inbar.backend.specification.enum.FilterOperator
import by.inbar.backend.specification.enum.MatchMode
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification
import java.time.Instant

abstract class AbstractSpecification<T>(
    val filter: Filter
) : Specification<T> {
    /**
     * Implement to add filters for necessary fields.
     *
     * e.g.
     * ```
     * override fun setup(builder: PredicateBuilder<T>) {
     *     builder.addDateSupport("createdTs")
     * }
     * ```
     *
     * or
     *
     * ```
     * override fun setup(builder: PredicateBuilder<T>) = with(builder) {
     *     addDateSupport("createdTs")
     * }
     * ```
     *
     * @param builder configuration object for adding filters
     */
    open fun setup(builder: PredicateBuilder<T>) {}

    final override fun toPredicate(
        root: Root<T>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        val builder = PredicateBuilder(root, criteriaBuilder, filter)
        setup(builder)
        query.distinct(true)
        return builder.build()
    }

    final override fun and(other: Specification<T>?): Specification<T> {
        return super.and(other)
    }

    final override fun or(other: Specification<T>?): Specification<T> {
        return super.or(other)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractSpecification<*>) return false

        return filter == other.filter
    }

    override fun hashCode(): Int {
        return filter.hashCode()
    }

    class PredicateBuilder<T>(
        private val root: Root<T>,
        private val criteriaBuilder: CriteriaBuilder,
        private val filter: Filter
    ) {
        private val supportedMatchModes = mutableMapOf<String, MutableSet<MatchMode>>()
        private val valueConverters = mutableMapOf<String, (String) -> Any>()
        private val customPredicates = mutableMapOf<String, Predicate>()
        private val customPredicateLastOperators = mutableMapOf<String, FilterOperator>()

        /**
         * Indicates whether to throw exception on unknown MatchMode.
         * If set to false *any* unknown and/or error match modes will result in empty search result.
         */
        var throwOnUnknownMatchMode = true

        /**
         * Indicated whether to throw exception on unsupported MatchMode.
         * If set to false *any* not registered match mode for some field will result in empty search result.
         */
        var throwOnUnsupportedMatchMode = true

        /**
         * Indicates whether to throw exception on unknown Operator.
         * If set to false will result in operator being `AND`.
         */
        var throwOnUnknownOperator = true

        fun build(): Predicate {
            var resultPredicate = criteriaBuilder.conjunction()
            filter.filters.forEach { (field, filterMetadataList) ->
                var fieldPredicate: Predicate? = null
                var previousOperator: FilterOperator? = null

                filterMetadataList.forEach { metadata ->
                    val matchMode = MatchMode.fromString(metadata.matchMode)
                        ?: if (throwOnUnknownMatchMode) throw IllegalArgumentException("No such match mode is known ${metadata.matchMode}")
                        else return criteriaBuilder.disjunction()

                    if (!isSupported(field, matchMode)) {
                        if (throwOnUnsupportedMatchMode)
                            throw IllegalArgumentException("Match mode $matchMode is not supported for field $field")
                        else
                            return criteriaBuilder.disjunction()
                    }

                    val filterPredicate = customPredicates[field]
                        ?: createPredicateForFilter(field, matchMode, metadata, ::createPredicate)

                    val (newResult, newPrevious) = fieldPredicate.addNextWithOperator(
                        previousOperator,
                        filterPredicate,
                        metadata.operator
                    )

                    fieldPredicate = newResult
                    previousOperator = newPrevious
                }

                resultPredicate = criteriaBuilder.and(resultPredicate, fieldPredicate ?: criteriaBuilder.conjunction())
            }
            return resultPredicate
        }

        /**
         * Adds support for `lessThan` and `greaterThan` filters.
         * Shorthand for `addLessThanSupport` and `addGreaterThanSupport`.
         *
         * @param E type of the comparable object
         * @param fieldName name of the field to compare
         * @param valueConverter converts string value of the filter to corresponding type
         */
        fun <E : Comparable<E>> addComparableSupport(fieldName: String, valueConverter: (String) -> E) {
            addFieldSupport(fieldName, MatchMode.LT)
            addFieldSupport(fieldName, MatchMode.GT)
            valueConverters[fieldName] = valueConverter
        }

        /**
         * Adds support for `lessThan` filter.
         *
         * @param E type of the comparable object
         * @param fieldName name of the field to compare
         * @param valueConverter converts string value of the filter to corresponding type
         */
        fun <E : Comparable<E>> addLessThanSupport(fieldName: String, valueConverter: (String) -> E) {
            addFieldSupport(fieldName, MatchMode.LT)
            valueConverters[fieldName] = valueConverter
        }

        /**
         * Adds support for `greaterThan` filter.
         *
         * @param E type of the comparable object
         * @param fieldName name of the field to compare
         * @param valueConverter converts string value of the filter to corresponding type
         */
        fun <E : Comparable<E>> addGreaterThanSupport(fieldName: String, valueConverter: (String) -> E) {
            addFieldSupport(fieldName, MatchMode.GT)
            valueConverters[fieldName] = valueConverter
        }

        /**
         * Adds support for `dateBefore` and `dateAfter` filters.
         * Requires Instant type of the field.
         * Shorthand for `addDateBeforeSupport` and `addDateAfterSupport`.
         *
         * P.S. in order to use other types (e.g. UNIX-timestamps use `addComparableSupport` method)
         *
         * @param fieldName name of the field to compare
         */
        fun addDateSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.DATE_LT)
            addFieldSupport(fieldName, MatchMode.DATE_GT)
            valueConverters[fieldName] = { Instant.parse(it) }
        }

        /**
         * Adds support for `dateBefore` filter.
         * Requires Instant type of the field.
         *
         * P.S. in order to use other types (e.g. UNIX-timestamps use `addLessThanSupport` method)
         *
         * @param fieldName name of the field to compare
         */
        fun addDateBeforeSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.DATE_LT)
            valueConverters[fieldName] = { Instant.parse(it) }
        }

        /**
         * Adds support for `dateAfter` filter.
         * Requires Instant type of the field.
         *
         * P.S. in order to use other types (e.g. UNIX-timestamps use `addGreaterThanSupport` method)
         *
         * @param fieldName name of the field to compare
         */
        fun addDateAfterSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.DATE_GT)
            valueConverters[fieldName] = { Instant.parse(it) }
        }

        /**
         * Adds support for `equal` filter.
         *
         * @param E type of the field
         * @param fieldName name of the field to compare
         * @param valueConverter converts string value of the filter to corresponding type
         */
        fun <E : Any> addEqualSupport(fieldName: String, valueConverter: (String) -> E) {
            addFieldSupport(fieldName, MatchMode.EQ)
            valueConverters[fieldName] = valueConverter
        }

        /**
         * Adds support for `in` filter.
         * This filter acts as `in` operator on arrays, e.g.
         * `'first' in ['first', 'second']` will result in true.
         *
         * @param fieldName name of the joined OneToMany field
         */
        fun addInSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.IN)
        }

        /**
         * Adds support for `startsWith` and `contains` filters.
         * Shorthand for `addStartsWithSupport` and `addContainsSupport`.
         *
         * @param fieldName name of the field to compare
         */
        fun addStringSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.STARTS_WITH)
            addFieldSupport(fieldName, MatchMode.CONTAINS)
        }

        /**
         * Adds support for `startsWith` filter.
         *
         * @param fieldName name of the field to compare
         */
        fun addStarsWithSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.STARTS_WITH)
        }

        /**
         * Adds support for `contains` filter.
         *
         * @param fieldName name of the field to compare
         */
        fun addContainsSupport(fieldName: String) {
            addFieldSupport(fieldName, MatchMode.CONTAINS)
        }

        /**
         * Add custom predicate for the field and corresponding match mode if any of other support method
         * does not apply.
         *
         * @param fieldName name of the build to add custom support to
         * @param matchMode match mode of the support
         * @param builder builder function for the predicate, accepts value of the filter
         */
        fun addCustomSupport(
            fieldName: String,
            matchMode: MatchMode,
            builder: (root: Root<T>, cb: CriteriaBuilder, value: String) -> Predicate
        ) {
            addFieldSupport(fieldName, matchMode)
            val filterMetadataList = filter.filters[fieldName] ?: return

            filterMetadataList.forEach { metadata ->
                val metadataMatchMode = MatchMode.fromString(metadata.matchMode)
                    ?: if (throwOnUnknownMatchMode) throw IllegalArgumentException("No such match mode is known ${metadata.matchMode}")
                    else return

                if (metadataMatchMode != matchMode) return@forEach

                val filterPredicate = createPredicateForFilter(fieldName, matchMode, metadata) { _, _, value ->
                    builder(root, criteriaBuilder, value)
                }
                val (newResult, newPrevious) = customPredicates[fieldName].addNextWithOperator(
                    customPredicateLastOperators[fieldName],
                    filterPredicate,
                    metadata.operator
                )
                customPredicates[fieldName] = newResult ?: criteriaBuilder.conjunction()
                customPredicateLastOperators[fieldName] = newPrevious
            }
        }

        private fun addFieldSupport(fieldName: String, matchMode: MatchMode) {
            val modes = supportedMatchModes.getOrPut(fieldName) { mutableSetOf() }
            modes.add(matchMode)
        }

        private fun isSupported(fieldName: String, matchMode: MatchMode): Boolean {
            val modes = supportedMatchModes[fieldName]
            return modes != null && modes.contains(matchMode)
        }

        private fun createPredicateForFilter(
            field: String,
            matchMode: MatchMode,
            metadata: FilterMetadata,
            builder: (fieldName: String, matchMode: MatchMode, value: String) -> Predicate
        ): Predicate? {
            if (metadata.value == null) return null
            var filterPredicate: Predicate? = null

            metadata.value.forEach { value ->
                val predicate = builder(field, matchMode, value)
                filterPredicate = criteriaBuilder.or(
                    filterPredicate ?: criteriaBuilder.disjunction(), predicate
                )
            }
            return filterPredicate
        }

        private fun createPredicate(fieldName: String, matchMode: MatchMode, value: String): Predicate {
            return when (matchMode) {
                MatchMode.EQ -> createPredicateEqual(fieldName, value)
                MatchMode.LT -> createPredicateLessThan(fieldName, value)
                MatchMode.GT -> createPredicateGreaterThan(fieldName, value)
                MatchMode.DATE_LT -> createPredicateLessThan(fieldName, value)
                MatchMode.DATE_GT -> createPredicateGreaterThan(fieldName, value)
                MatchMode.STARTS_WITH -> createPredicateStartsWith(fieldName, value)
                MatchMode.CONTAINS -> createPredicateContains(fieldName, value)
                MatchMode.IN -> createPredicateIn(fieldName, value)
            }
        }

        private fun createPredicateEqual(fieldName: String, value: String): Predicate {
            return criteriaBuilder.equal(root.get<Any>(fieldName), valueConverters[fieldName]!!.invoke(value))
        }

        private fun createPredicateStartsWith(fieldName: String, value: String): Predicate {
            return criteriaBuilder.like(root.get<Any>(fieldName).`as`(String::class.java), "$value%")
        }

        private fun createPredicateContains(fieldName: String, value: String): Predicate {
            return criteriaBuilder.like(root.get<Any>(fieldName).`as`(String::class.java), "%$value%")
        }

        @Suppress("UNCHECKED_CAST") // converter is used from generic abstraction
        private fun createPredicateGreaterThan(fieldName: String, value: String): Predicate {
            val converted = valueConverters[fieldName]!!.invoke(value) as Comparable<Any>
            return criteriaBuilder.greaterThan(root.get(fieldName), converted)
        }

        @Suppress("UNCHECKED_CAST") // converter is used from generic abstraction
        private fun createPredicateLessThan(fieldName: String, value: String): Predicate {
            val converted = valueConverters[fieldName]!!.invoke(value) as Comparable<Any>
            return criteriaBuilder.lessThan(root.get(fieldName), converted)
        }

        private fun createPredicateIn(fieldName: String, value: String): Predicate {
            return root.join<T, List<String>>(fieldName).`in`(value)
        }

        private fun Predicate?.addNextWithOperator(
            previousOperator: FilterOperator?,
            nextPredicate: Predicate?,
            nextOperator: String?
        ): Pair<Predicate?, FilterOperator> {
            val newPrevious = if (nextOperator == null) {
                FilterOperator.AND
            } else {
                FilterOperator.fromString(nextOperator)
                    ?: if (throwOnUnknownOperator) throw IllegalArgumentException("No such operator is known $nextOperator")
                    else FilterOperator.AND
            }

            return if (this == null) {
                nextPredicate to newPrevious
            } else {
                if (previousOperator == null) throw IllegalStateException("Field predicate is know for unknown operator")
                val newFieldPredicate = when (previousOperator) {
                    FilterOperator.AND -> criteriaBuilder.and(this, nextPredicate ?: criteriaBuilder.conjunction())
                    FilterOperator.OR -> criteriaBuilder.or(this, nextPredicate ?: criteriaBuilder.disjunction())
                }
                newFieldPredicate to newPrevious
            }
        }
    }
}
