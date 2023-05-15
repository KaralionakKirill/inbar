package by.inbar.backend.dto.model.user

import by.inbar.backend.dto.AbstractDto

data class AuthenticationRequest(
    var email: String,

    var password: String,
) : AbstractDto()
