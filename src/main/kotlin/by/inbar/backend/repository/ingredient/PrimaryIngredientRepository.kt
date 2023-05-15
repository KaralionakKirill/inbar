package by.inbar.backend.repository.ingredient

import by.inbar.backend.model.ingredient.PrimaryIngredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrimaryIngredientRepository : JpaRepository<PrimaryIngredient, Int>
