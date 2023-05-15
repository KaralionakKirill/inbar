package by.inbar.backend.controller

import by.inbar.backend.dto.model.user.AuthenticationRequest
import by.inbar.backend.dto.model.user.AuthenticationResponse
import by.inbar.backend.dto.model.user.RegistrationRequest
import by.inbar.backend.logger.logger
import by.inbar.backend.service.auth.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    private val logger = this.logger()

    @PostMapping("/register")
    fun register(@RequestBody registrationRequest: RegistrationRequest): AuthenticationResponse {
        logger.info("Accept registration request.")
        return authService.register(registrationRequest)
    }

    @PostMapping("/authenticate")
    fun register(@RequestBody authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        logger.info("Accept authentication request.")
        return authService.authenticate(authenticationRequest)
    }
}
