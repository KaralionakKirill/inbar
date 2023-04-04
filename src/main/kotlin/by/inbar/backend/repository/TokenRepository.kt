package by.inbar.backend.repository

import by.inbar.backend.model.token.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    @Query(
        value = """
            select t from Token t inner join User u on t.user.id = u.id 
            where u.id = :id and (t.expired = false and t.revoked = false)
        """
    )
    fun findAllValidTokenByUser(id: Long): List<Token>

    fun findByToken(token: String): Optional<Token>
}
