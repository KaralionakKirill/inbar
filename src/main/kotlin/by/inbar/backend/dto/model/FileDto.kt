package by.inbar.backend.dto.model

import by.inbar.backend.dto.AbstractDto
import java.time.Instant

data class FileDto(
    var id: Long,

    var name: String?,

    var mimeType: String,

    var createdTs: Instant
) : AbstractDto()
