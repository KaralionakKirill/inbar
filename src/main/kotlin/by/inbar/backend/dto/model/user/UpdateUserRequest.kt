package by.inbar.backend.dto.model.user

import by.inbar.backend.dto.AbstractDto

data class UpdateUserRequest(
    var firstname: String?,

    var lastname: String?,

    var aboutMe: String?,

    var avatarId: Long?
) : AbstractDto()
