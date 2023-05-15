package by.inbar.backend.repository.cocktail

import by.inbar.backend.model.cocktail.CocktailGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailGroupRepository : JpaRepository<CocktailGroup, Int>
