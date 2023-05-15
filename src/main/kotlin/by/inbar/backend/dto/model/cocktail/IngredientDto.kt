package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.dto.model.common.MeasureDto
import by.inbar.backend.dto.model.ingredient.IngredientShort

data class IngredientDto(
    var id: Long,

    var value: Int,

    var ingredient: IngredientShort,

    var measure: MeasureDto
) : AbstractDto()
