package by.inbar.backend.repository.common

import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.common.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByCocktail(cocktail: Cocktail): List<Comment>
}
