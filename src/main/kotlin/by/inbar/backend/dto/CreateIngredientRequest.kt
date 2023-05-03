package by.inbar.backend.dto

import by.inbar.backend.model.composition.AlcoholDegree
import by.inbar.backend.model.composition.PrimaryIngredient
import by.inbar.backend.model.composition.Taste
import by.inbar.backend.model.ingredient.IngredientType

data class CreateIngredientRequest(
    val name: String,

    val description: String,

    val imageId: Long,

    val type: IngredientType,

    val primaryIngredient: PrimaryIngredient,

    val alcoholDegree: AlcoholDegree,

    val taste: Taste?
) : AbstractDto()
