package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.AbstractDto

data class UpdateIngredientResponse(
    val id: Long,

    val name: String
) : AbstractDto()
