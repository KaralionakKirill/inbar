package by.inbar.backend.mapper

import by.inbar.backend.dto.model.user.UserInfo
import by.inbar.backend.model.user.User

fun User.toDto() = UserInfo(
    firstname,
    lastname,
    email,
    avatar?.id,
    aboutMe,
    role
)
