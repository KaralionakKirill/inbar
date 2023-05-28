package by.inbar.backend.service.model

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.exception.NotFoundException
import by.inbar.backend.model.user.Role
import by.inbar.backend.model.user.User
import by.inbar.backend.repository.user.UserRepository
import by.inbar.backend.specification.UserSpecification
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun getBartenders(): List<User> {
        return userRepository.findAllByRole(Role.BARTENDER)
    }

    fun getById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { throw NotFoundException("User with id=$id not found") }
    }

    fun findAllByFilter(filter: Filter): Page<User> {
        return userRepository.findAll(UserSpecification(filter), filter.toPageRequest())
    }
}
