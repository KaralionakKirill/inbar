package by.inbar.backend.dto.model.common

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.dto.model.user.UserInfo
import java.time.Instant

data class CommentDto(
    var rating: Int,

    var message: String,

    var author: UserInfo,

    var createdTs: Instant
) : AbstractDto()
