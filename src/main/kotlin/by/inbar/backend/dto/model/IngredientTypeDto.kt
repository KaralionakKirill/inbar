package by.inbar.backend.dto.model

import by.inbar.backend.dto.AbstractDto

data class IngredientTypeDto(
    var id: Int,

    var name: String,

    var ingredientsAmount: Int
) : AbstractDto()
