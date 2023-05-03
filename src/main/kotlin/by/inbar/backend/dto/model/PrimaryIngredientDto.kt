package by.inbar.backend.dto.model

import by.inbar.backend.dto.AbstractDto

data class PrimaryIngredientDto(
    var id: Int,

    var name: String
) : AbstractDto()
