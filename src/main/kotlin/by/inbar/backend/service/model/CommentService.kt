package by.inbar.backend.service.model

import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.common.Comment
import by.inbar.backend.repository.common.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    fun findAllByCocktail(cocktail: Cocktail): List<Comment> {
        return commentRepository.findAllByCocktail(cocktail)
    }
}
