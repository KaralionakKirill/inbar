package by.inbar.backend.specification

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.cocktail.CocktailGroup
import by.inbar.backend.model.common.AlcoholDegree
import by.inbar.backend.model.common.CookingMethod
import by.inbar.backend.model.common.Taste
import by.inbar.backend.specification.enum.MatchMode

class CocktailSpecification(
    filter: Filter
) : AbstractSpecification<Cocktail>(filter) {
    override fun setup(builder: PredicateBuilder<Cocktail>) = with(builder) {
        addStringSupport(Cocktail::name.name)
        addCustomSupport("cookingMethod", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Cocktail, CookingMethod>("cookingMethod")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("cocktailGroup", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Cocktail, CocktailGroup>("cocktailGroup")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("alcoholDegree", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Cocktail, AlcoholDegree>("alcoholDegree")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("taste", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Cocktail, Taste>("taste")
                    .get("name"),
                "%$value%"
            )
        }
    }
}
