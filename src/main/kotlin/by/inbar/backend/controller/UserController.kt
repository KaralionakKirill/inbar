package by.inbar.backend.controller

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.dto.model.user.UpdateUserRequest
import by.inbar.backend.dto.model.user.UserInfo
import by.inbar.backend.service.UserFacade
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @GetMapping("/bartenders")
    fun getBartenders(): List<UserInfo> {
        return userFacade.getBartenders()
    }

    @PutMapping
    fun updateUser(@RequestBody request: UpdateUserRequest): UserInfo {
        return userFacade.updateUser(request)
    }

    @PutMapping("/like/cocktail/{cocktailId}")
    fun likeCocktail(@PathVariable cocktailId: Long, @RequestParam username: String): UserInfo {
        return userFacade.likeCocktail(cocktailId, username)
    }

    @PostMapping
    fun getUsersByFilter(@RequestBody filter: Filter): Page<UserInfo> {
        return userFacade.findAllByFilter(filter)
    }
}
