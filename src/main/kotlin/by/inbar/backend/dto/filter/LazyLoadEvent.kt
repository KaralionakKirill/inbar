package by.inbar.backend.dto.filter

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

data class LazyLoadEvent(
    val first: Int,
    val rows: Int,
    var sortField: String?,
    var sortOrder: Int,
    val filters: Map<String, List<FilterMetadata>>
) {
    fun toPageRequest(): PageRequest {
        val sort = if (sortField != null) {
            val direction = if (sortOrder == 1) Sort.Direction.ASC else Sort.Direction.DESC
            Sort.by(direction, sortField)
        } else {
            null
        }

        return if (sort != null) {
            PageRequest.of(first / rows, rows, sort)
        } else {
            PageRequest.of(first / rows, rows)
        }
    }
}
