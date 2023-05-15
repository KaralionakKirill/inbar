package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.AbstractDto

data class CreateCocktailResponse(
    val id: Long,

    val name: String
) : AbstractDto()
