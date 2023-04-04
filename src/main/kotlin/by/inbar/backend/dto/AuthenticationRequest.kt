package by.inbar.backend.dto

data class AuthenticationRequest(
    var email: String,

    var password: String,
) : AbstractDto()
