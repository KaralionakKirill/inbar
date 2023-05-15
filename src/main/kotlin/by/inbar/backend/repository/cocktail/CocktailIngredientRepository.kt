package by.inbar.backend.repository.cocktail

import by.inbar.backend.model.cocktail.CocktailIngredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailIngredientRepository : JpaRepository<CocktailIngredient, Long>
