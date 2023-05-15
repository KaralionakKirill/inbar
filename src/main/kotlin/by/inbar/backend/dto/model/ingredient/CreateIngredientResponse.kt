package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.AbstractDto

data class CreateIngredientResponse(
    val id: Long,

    val name: String
) : AbstractDto()
