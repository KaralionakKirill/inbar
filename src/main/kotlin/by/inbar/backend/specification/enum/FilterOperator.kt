package by.inbar.backend.specification.enum

enum class FilterOperator {
    AND, OR;

    override fun toString(): String = when (this) {
        AND -> "and"
        OR -> "or"
    }

    companion object {
        fun fromString(operator: String): FilterOperator? = when (operator) {
            "and" -> AND
            "or" -> OR
            else -> null
        }
    }
}
