package by.inbar.backend.service.model

import by.inbar.backend.model.user.User
import by.inbar.backend.repository.UserRepository
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
}
