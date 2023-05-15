package by.inbar.backend.service.auth

import by.inbar.backend.dto.model.user.AuthenticationRequest
import by.inbar.backend.dto.model.user.AuthenticationResponse
import by.inbar.backend.dto.model.user.RegistrationRequest
import by.inbar.backend.exception.UserAlreadyExistException
import by.inbar.backend.extension.toUserDetails
import by.inbar.backend.model.token.Token
import by.inbar.backend.model.user.Role
import by.inbar.backend.model.user.User
import by.inbar.backend.repository.user.TokenRepository
import by.inbar.backend.security.JwtService
import by.inbar.backend.service.model.UserService
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@Transactional
class AuthService(
    private val userService: UserService,

    private val tokenRepository: TokenRepository,

    private val passwordEncoder: PasswordEncoder,

    private val jwtService: JwtService,

    private val authenticationManager: AuthenticationManager
) {
    fun register(registrationRequest: RegistrationRequest): AuthenticationResponse =
        with(registrationRequest) {
            userService.findByEmail(email).ifPresent { throw UserAlreadyExistException("User with email=$email exist") }

            val user = userService.save(
                User(
                    firstname,
                    lastname,
                    email,
                    passwordEncoder.encode(password),
                    if (professional) Role.BARTENDER else Role.USER
                )
            )
            val jwtToken = jwtService.generateToken(user.toUserDetails())
            saveUserToken(user, jwtToken)
            AuthenticationResponse(jwtToken)
        }

    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse =
        with(authenticationRequest) {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(email, password)
            )
            val user = userService.findByEmail(email)
                .orElseThrow { UsernameNotFoundException("User not found") }
            val jwtToken = jwtService.generateToken(user.toUserDetails())
            revokeAllUserTokens(user)
            saveUserToken(user, jwtToken)
            AuthenticationResponse(jwtToken)
        }

    private fun saveUserToken(user: User, jwtToken: String) {
        tokenRepository.save(Token(user, jwtToken))
    }

    private fun revokeAllUserTokens(user: User) {
        val validTokens = tokenRepository.findAllValidTokenByUser(user.id)
        if (validTokens.isEmpty()) return
        validTokens.forEach {
            it.expired = true
            it.revoked = true
        }
        tokenRepository.saveAll(validTokens)
    }
}
