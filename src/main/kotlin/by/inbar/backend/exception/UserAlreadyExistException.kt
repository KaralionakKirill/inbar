package by.inbar.backend.exception

import org.springframework.security.core.AuthenticationException

open class UserAlreadyExistException(message: String) : AuthenticationException(message)
