package by.inbar.backend.controller

import by.inbar.backend.dto.model.user.UpdateUserRequest
import by.inbar.backend.dto.model.user.UserInfo
import by.inbar.backend.service.UserFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userFacade: UserFacade
) {
    @GetMapping("/{name}")
    fun getUserInfo(@PathVariable name: String): UserInfo {
        return userFacade.getUserInfo(name)
    }

    @PostMapping("/{name}")
    fun updateUser(@PathVariable name: String, @RequestBody updateUserRequest: UpdateUserRequest): UserInfo {
        return userFacade.updateUser(name, updateUserRequest)
    }
}
