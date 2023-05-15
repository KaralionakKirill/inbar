package by.inbar.backend.service.model

import by.inbar.backend.exception.NotFoundException
import by.inbar.backend.model.cocktail.CocktailIngredient
import by.inbar.backend.repository.cocktail.CocktailIngredientRepository
import org.springframework.stereotype.Service

@Service
class CocktailIngredientService(
    private val cocktailIngredientRepository: CocktailIngredientRepository
) {
    fun save(cocktailIngredient: CocktailIngredient): CocktailIngredient {
        return cocktailIngredientRepository.save(cocktailIngredient)
    }

    fun findAll(): MutableList<CocktailIngredient> {
        return cocktailIngredientRepository.findAll()
    }

    fun getById(id: Long): CocktailIngredient {
        return cocktailIngredientRepository.findById(id)
            .orElseThrow { throw NotFoundException("CocktailIngredient with id=$id not found") }
    }

    fun saveAll(cocktailIngredients: List<CocktailIngredient>): MutableList<CocktailIngredient> {
        return cocktailIngredientRepository.saveAll(cocktailIngredients)
    }
}
