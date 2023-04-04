package by.inbar.backend.service

import by.inbar.backend.dto.AuthenticationRequest
import by.inbar.backend.dto.AuthenticationResponse
import by.inbar.backend.dto.RegistrationRequest
import by.inbar.backend.extension.toUserDetails
import by.inbar.backend.model.token.Token
import by.inbar.backend.model.user.Role
import by.inbar.backend.model.user.User
import by.inbar.backend.repository.TokenRepository
import by.inbar.backend.repository.UserRepository
import by.inbar.backend.security.JwtService
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@Transactional
class AuthService(
    private val userRepository: UserRepository,

    private val tokenRepository: TokenRepository,

    private val passwordEncoder: PasswordEncoder,

    private val jwtService: JwtService,

    private val authenticationManager: AuthenticationManager
) {
    fun register(registrationRequest: RegistrationRequest): AuthenticationResponse =
        with(registrationRequest) {
            val user = userRepository.save(
                User(
                    firstname,
                    lastname,
                    email,
                    passwordEncoder.encode(password),
                    Role.USER
                )
            )
            val jwtToken = jwtService.generateToken(user.toUserDetails())
            saveUserToken(user, jwtToken)
            AuthenticationResponse(jwtToken)
        }

    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse =
        with(authenticationRequest) {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    email, password
                )
            )
            val user = userRepository.findByEmail(email)
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
