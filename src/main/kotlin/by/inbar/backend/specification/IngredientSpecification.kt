package by.inbar.backend.specification

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.model.common.AlcoholDegree
import by.inbar.backend.model.common.Taste
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.model.ingredient.IngredientGroup
import by.inbar.backend.model.ingredient.IngredientType
import by.inbar.backend.model.ingredient.PrimaryIngredient
import by.inbar.backend.specification.enum.MatchMode

class IngredientSpecification(
    filter: Filter
) : AbstractSpecification<Ingredient>(filter){
    override fun setup(builder: PredicateBuilder<Ingredient>) = with(builder) {
        addStringSupport(Ingredient::name.name)
        addCustomSupport("ingredientType", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Ingredient, IngredientType>("type")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("ingredientGroup", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Ingredient, IngredientGroup>("group")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("primaryIngredient", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Ingredient, PrimaryIngredient>("PrimaryIngredient")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("alcoholDegree", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Ingredient, AlcoholDegree>("alcoholDegree")
                    .get("name"),
                "%$value%"
            )
        }
        addCustomSupport("taste", MatchMode.EQ) { root, cb, value ->
            cb.like(
                root.join<Ingredient, Taste>("taste")
                    .get("name"),
                "%$value%"
            )
        }
    }
}
