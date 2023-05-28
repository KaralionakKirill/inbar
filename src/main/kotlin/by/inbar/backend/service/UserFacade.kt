package by.inbar.backend.service

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.dto.model.user.UpdateUserRequest
import by.inbar.backend.dto.model.user.UserInfo
import by.inbar.backend.mapper.toDto
import by.inbar.backend.service.model.CocktailService
import by.inbar.backend.service.model.FileService
import by.inbar.backend.service.model.UserService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.Instant

@Service
@Transactional
class UserFacade(
    private val userService: UserService,

    private val fileService: FileService,

    private val cocktailService: CocktailService
) {
    fun getUserInfo(name: String): UserInfo {
        val user = userService.findByEmail(name)
            .orElseThrow { UsernameNotFoundException("User not found") }
        return user.toDto()
    }

    fun updateUser(request: UpdateUserRequest): UserInfo = with(request) {
        val user = userService.getById(request.id)

        val updatedUser = userService.save(
            user.apply {
                if (request.firstname != null) firstname = request.firstname!!
                if (request.lastname != null) lastname = request.lastname!!
                if (request.aboutMe != null) aboutMe = request.aboutMe!!
                if (request.avatarId != null) avatar = fileService.getById(avatarId!!)
                if (request.role != null) role = request.role!!
                modifiedTs = Instant.now()
            }
        )
        return updatedUser.toDto()
    }

    fun likeCocktail(cocktailId: Long, username: String): UserInfo {
        val user = userService.findByEmail(username)
            .orElseThrow { UsernameNotFoundException("User not found") }
        val cocktail = cocktailService.getById(cocktailId)

        if (user.likedCocktails.contains(cocktail)) {
            cocktail.likedByUsers.remove(user)
            user.likedCocktails.remove(cocktail)
        } else {
            cocktail.likedByUsers.add(user)
            user.likedCocktails.add(cocktail)
        }
        cocktailService.save(cocktail)
        return userService.save(user).toDto()
    }

    fun getBartenders(): List<UserInfo> {
        return userService.getBartenders().map { it.toDto() }
    }

    fun findAllByFilter(filter: Filter): Page<UserInfo> {
        return userService.findAllByFilter(filter).map { it.toDto() }
    }
}
