package by.inbar.backend.repository.ingredient

import by.inbar.backend.model.ingredient.IngredientType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientTypeRepository : JpaRepository<IngredientType, Int>
