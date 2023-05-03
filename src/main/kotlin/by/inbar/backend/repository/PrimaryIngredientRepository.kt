package by.inbar.backend.repository

import by.inbar.backend.model.composition.PrimaryIngredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrimaryIngredientRepository : JpaRepository<PrimaryIngredient, Int>
