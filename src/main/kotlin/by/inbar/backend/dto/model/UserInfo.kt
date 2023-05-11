package by.inbar.backend.dto.model

import by.inbar.backend.model.user.Role

data class UserInfo(
    var firstname: String,

    var lastname: String,

    var email: String,

    var avatarId: Long?,

    var aboutMe: String?,

    var role: Role
)
