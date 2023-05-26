package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.AbstractDto

data class UpdateCocktailResponse(
    val id: Long,

    val name: String
) : AbstractDto()
