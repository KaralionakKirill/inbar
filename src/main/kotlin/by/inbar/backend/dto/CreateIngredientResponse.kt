package by.inbar.backend.dto

data class CreateIngredientResponse(
    val id: Long,

    val name: String
) : AbstractDto()
