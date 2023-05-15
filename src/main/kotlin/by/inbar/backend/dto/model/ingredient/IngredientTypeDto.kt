package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.AbstractDto

data class IngredientTypeDto(
    var id: Int,

    var name: String,

    var imageName: String,

    var ingredientsAmount: Int
) : AbstractDto()
