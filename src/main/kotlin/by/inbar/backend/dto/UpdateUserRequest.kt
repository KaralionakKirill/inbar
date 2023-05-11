package by.inbar.backend.dto

data class UpdateUserRequest(
    var firstname: String?,

    var lastname: String?,

    var aboutMe: String?,

    var avatarId: Long?
) : AbstractDto()
