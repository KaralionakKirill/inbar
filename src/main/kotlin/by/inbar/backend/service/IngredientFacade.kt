package by.inbar.backend.service

import by.inbar.backend.dto.CreateIngredientRequest
import by.inbar.backend.dto.CreateIngredientResponse
import by.inbar.backend.dto.model.IngredientFull
import by.inbar.backend.dto.model.IngredientShort
import by.inbar.backend.mapper.toFull
import by.inbar.backend.mapper.toShort
import by.inbar.backend.model.Status
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.service.model.FileService
import by.inbar.backend.service.model.IngredientService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class IngredientFacade(
    private val ingredientService: IngredientService,

    private val fileService: FileService
) {
    fun createIngredient(request: CreateIngredientRequest): CreateIngredientResponse = with(request) {
        val image = fileService.getById(imageId)
        val ingredient = ingredientService.save(
            Ingredient(name, description, type, image, primaryIngredient, alcoholDegree, taste, Status.PENDING)
        )
        CreateIngredientResponse(ingredient.id, ingredient.name)
    }

    fun getIngredients(): List<IngredientShort> {
        return ingredientService.findAll().map { it.toShort() }
    }

    fun getIngredientById(id: Long): IngredientFull {
        return ingredientService.getById(id).toFull()
    }
}
