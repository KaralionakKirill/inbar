package by.inbar.backend.dto

data class RegistrationRequest(
    var firstname: String,

    var lastname: String,

    var email: String,

    var password: String
) : AbstractDto()
