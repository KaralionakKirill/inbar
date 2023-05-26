package by.inbar.backend.specification.enum

enum class MatchMode {
    GT, LT, EQ, DATE_GT, DATE_LT, CONTAINS, STARTS_WITH, IN;

    override fun toString(): String = when (this) {
        GT -> "greaterThan"
        LT -> "lessThan"
        EQ -> "equals"
        DATE_GT -> "dateAfter"
        DATE_LT -> "dateBefore"
        CONTAINS -> "contains"
        STARTS_WITH -> "startsWith"
        IN -> "in"
    }

    companion object {
        fun fromString(matchMode: String): MatchMode? = when (matchMode) {
            "greaterThan" -> GT
            "lessThan" -> LT
            "equals" -> EQ
            "dateAfter" -> DATE_GT
            "dateBefore" -> DATE_LT
            "contains" -> CONTAINS
            "startsWith" -> STARTS_WITH
            "in" -> IN
            else -> null
        }
    }
}
