package by.inbar.backend.service

import by.inbar.backend.dto.UpdateUserRequest
import by.inbar.backend.dto.model.UserInfo
import by.inbar.backend.exception.ServiceException
import by.inbar.backend.mapper.toDto
import by.inbar.backend.service.model.FileService
import by.inbar.backend.service.model.UserService
import jakarta.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class UserFacade(
    private val userService: UserService,

    private val fileService: FileService
) {
    fun getUserInfo(name: String): UserInfo {
        val user = userService.findByEmail(name)
            .orElseThrow { UsernameNotFoundException("User not found") }
        return user.toDto()
    }

    fun updateUser(name: String, updateUserRequest: UpdateUserRequest): UserInfo {
        val contextName = SecurityContextHolder.getContext().authentication.name
        if (contextName != name) throw ServiceException("Haven't permission!")

        val user = userService.findByEmail(name)
            .orElseThrow { UsernameNotFoundException("User not found") }
        val updatedUser = userService.save(user.apply {
            if (updateUserRequest.firstname != null) firstname = updateUserRequest.firstname!!
            if (updateUserRequest.lastname != null) lastname = updateUserRequest.lastname!!
            if (updateUserRequest.aboutMe != null) aboutMe = updateUserRequest.aboutMe!!
            if (updateUserRequest.avatarId != null) {
                avatar = fileService.getById(updateUserRequest.avatarId!!)
            }
        })
        return updatedUser.toDto()
    }
}
