package by.inbar.backend.repository.user

import by.inbar.backend.model.user.Role
import by.inbar.backend.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByEmail(email: String): Optional<User>

    fun findAllByRole(role: Role): List<User>
}
