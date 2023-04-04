package by.inbar.backend.service

import by.inbar.backend.constant.AUTHORIZATION_HEADER
import by.inbar.backend.logger.logger
import by.inbar.backend.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service

@Service
class LogoutService(
    private val tokenRepository: TokenRepository
) : LogoutHandler {
    private val logger = this.logger()

    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?
    ) {
        logger.info("Accept logout request.")
        val authHeader = request.getHeader(AUTHORIZATION_HEADER)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        val jwtToken = authHeader.substring(7)
        val storedToken = tokenRepository.findByToken(jwtToken)
        storedToken.ifPresent {
            it.expired = true
            it.revoked = true
            tokenRepository.save(it)
            SecurityContextHolder.clearContext()
        }
    }
}
