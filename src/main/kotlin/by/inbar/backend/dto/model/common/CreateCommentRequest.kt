package by.inbar.backend.dto.model.common

import by.inbar.backend.dto.AbstractDto

data class CreateCommentRequest(
    var rating: Int,

    var message: String,

    var cocktailId: Long,

    var authorName: String
) : AbstractDto()
