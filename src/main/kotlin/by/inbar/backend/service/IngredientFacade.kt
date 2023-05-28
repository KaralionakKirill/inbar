package by.inbar.backend.service

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.dto.model.ingredient.CreateIngredientRequest
import by.inbar.backend.dto.model.ingredient.CreateIngredientResponse
import by.inbar.backend.dto.model.ingredient.IngredientFull
import by.inbar.backend.dto.model.ingredient.IngredientShort
import by.inbar.backend.dto.model.ingredient.UpdateIngredientRequest
import by.inbar.backend.dto.model.ingredient.UpdateIngredientResponse
import by.inbar.backend.mapper.toEntity
import by.inbar.backend.mapper.toFull
import by.inbar.backend.mapper.toShort
import by.inbar.backend.model.common.Status
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.model.user.Role
import by.inbar.backend.service.model.FileService
import by.inbar.backend.service.model.IngredientService
import by.inbar.backend.service.model.UserService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class IngredientFacade(
    private val ingredientService: IngredientService,

    private val fileService: FileService,

    private val userService: UserService
) {
    fun createIngredient(request: CreateIngredientRequest): CreateIngredientResponse = with(request) {
        val image = fileService.getById(imageId)
        val user = userService.findByEmail(author)
            .orElseThrow { UsernameNotFoundException("User not found") }

        val status = if (user.role == Role.ADMIN) Status.REVIEWED else Status.PENDING
        val ingredient = ingredientService.save(
            Ingredient(
                name,
                description,
                type.toEntity(),
                group.toEntity(),
                image,
                primaryIngredient.toEntity(),
                alcoholDegree.toEntity(),
                taste.toEntity(),
                status
            )
        )
        CreateIngredientResponse(ingredient.id, ingredient.name)
    }

    fun updateIngredient(request: UpdateIngredientRequest): UpdateIngredientResponse {
        val updatedImage = fileService.getById(request.imageId)
        val ingredient = ingredientService.getById(request.id)
        if (ingredient.image.id != request.imageId) fileService.delete(ingredient.image.id)

        val updatedIngredient = ingredientService.save(
            ingredient.apply {
                name = request.name
                description = request.description
                type = request.type.toEntity()
                group = request.group.toEntity()
                image = updatedImage
                primaryIngredient = request.primaryIngredient.toEntity()
                alcoholDegree = request.alcoholDegree.toEntity()
                taste = request.taste.toEntity()
                status = request.status
            }
        )
        return  UpdateIngredientResponse(updatedIngredient.id, updatedIngredient.name)
    }

    fun getIngredients(): List<IngredientShort> {
        return ingredientService.findAll().map { it.toShort() }
    }

    fun getIngredientById(id: Long): IngredientFull {
        return ingredientService.getById(id).toFull()
    }

    fun findAllByFilter(filter: Filter): Page<IngredientShort> {
        return ingredientService.findAllByFilter(filter).map { it.toShort() }
    }
}
