package by.inbar.backend.repository.cocktail

import by.inbar.backend.model.cocktail.Cocktail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CocktailRepository : JpaRepository<Cocktail, Long>, JpaSpecificationExecutor<Cocktail>
