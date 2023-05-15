package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.AbstractDto

data class IngredientGroupDto(
    var id: Int,

    var name: String,

    var cocktailBaseName: String?,

    var cocktailBase: Boolean,

    var instrument: Boolean
) : AbstractDto()
