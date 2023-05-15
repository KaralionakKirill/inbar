package by.inbar.backend.dto.model.user

import by.inbar.backend.dto.AbstractDto

data class RegistrationRequest(
    var firstname: String,

    var lastname: String,

    var email: String,

    var password: String,

    var professional: Boolean
) : AbstractDto()
