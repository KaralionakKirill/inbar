package by.inbar.backend.repository.ingredient

import by.inbar.backend.model.ingredient.IngredientGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientGroupRepository : JpaRepository<IngredientGroup, Int>
