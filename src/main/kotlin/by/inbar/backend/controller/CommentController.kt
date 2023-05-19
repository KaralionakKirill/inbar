package by.inbar.backend.controller

import by.inbar.backend.dto.model.common.CommentDto
import by.inbar.backend.dto.model.common.CreateCommentRequest
import by.inbar.backend.service.CommentFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comments")
class CommentController(
    private val commentFacade: CommentFacade
) {
    @PostMapping("/new")
    fun createComment(@RequestBody request: CreateCommentRequest) {
        commentFacade.createComment(request)
    }

    @GetMapping("/cocktail/{cocktailId}")
    fun findAllByCocktail(@PathVariable cocktailId: Long): List<CommentDto> {
        return commentFacade.findAllByCocktail(cocktailId)
    }
}
