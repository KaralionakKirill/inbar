package by.inbar.backend.repository.ingredient

import by.inbar.backend.model.ingredient.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Long>
