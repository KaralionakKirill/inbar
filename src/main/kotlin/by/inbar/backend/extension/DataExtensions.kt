package by.inbar.backend.extension

import by.inbar.backend.model.user.User
import by.inbar.backend.model.user.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails

fun User.toUserDetails(): UserDetails = UserDetailsImpl(this)
