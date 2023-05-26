package by.inbar.backend.dto.filter

data class FilterMetadata(
    val value: List<String>?,
    val matchMode: String,
    val operator: String?
)
