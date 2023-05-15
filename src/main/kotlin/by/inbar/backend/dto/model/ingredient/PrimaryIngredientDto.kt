package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.AbstractDto

data class PrimaryIngredientDto(
    var id: Int,

    var name: String
) : AbstractDto()
