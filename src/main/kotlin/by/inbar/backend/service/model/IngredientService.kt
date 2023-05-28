package by.inbar.backend.service.model

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.exception.NotFoundException
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.repository.ingredient.IngredientRepository
import by.inbar.backend.specification.IngredientSpecification
import org.springframework.data.domain.Page
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

    fun findAllByFilter(filter: Filter): Page<Ingredient> {
        return ingredientRepository.findAll(IngredientSpecification(filter), filter.toPageRequest())
    }
}
