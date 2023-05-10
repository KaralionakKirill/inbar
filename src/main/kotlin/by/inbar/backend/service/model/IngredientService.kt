package by.inbar.backend.service.model

import by.inbar.backend.exception.NotFoundException
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.repository.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientService(
    private val ingredientRepository: IngredientRepository
) {
    fun save(ingredient: Ingredient): Ingredient {
        return ingredientRepository.save(ingredient)
    }

    fun findAll(): MutableList<Ingredient> {
        return ingredientRepository.findAll()
    }

    fun getById(id: Long): Ingredient {
        return ingredientRepository.findById(id)
            .orElseThrow { throw NotFoundException("Ingredient with id=$id not found") }
    }
}
