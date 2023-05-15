package by.inbar.backend.dto.model.user

import by.inbar.backend.dto.AbstractDto

data class AuthenticationResponse(
    var token: String
) : AbstractDto()
