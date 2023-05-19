package by.inbar.backend.service

import by.inbar.backend.dto.model.common.CommentDto
import by.inbar.backend.dto.model.common.CreateCommentRequest
import by.inbar.backend.mapper.toDto
import by.inbar.backend.model.common.Comment
import by.inbar.backend.service.model.CocktailService
import by.inbar.backend.service.model.CommentService
import by.inbar.backend.service.model.UserService
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class CommentFacade(
    private val commentService: CommentService,

    private val cocktailService: CocktailService,

    private val userService: UserService
) {
    fun createComment(request: CreateCommentRequest) {
        with(request) {
            val user = userService.findByEmail(authorName)
                .orElseThrow { UsernameNotFoundException("User not found") }
            val cocktail = cocktailService.getById(cocktailId)

            commentService.save(Comment(rating, message, cocktail, user))
        }
    }

    fun findAllByCocktail(cocktailId: Long): List<CommentDto> {
        val cocktail = cocktailService.getById(cocktailId)
        return commentService.findAllByCocktail(cocktail).map { it.toDto() }
    }
}
