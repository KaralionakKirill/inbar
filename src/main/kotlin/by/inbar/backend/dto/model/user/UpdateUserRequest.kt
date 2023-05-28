package by.inbar.backend.dto.model.user

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.model.user.Role

data class UpdateUserRequest(
    var id: Long,

    var firstname: String?,

    var lastname: String?,

    var aboutMe: String?,

    var avatarId: Long?,

    var role: Role?
) : AbstractDto()
