package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.AbstractDto

data class CocktailAuthor(
    val id: Long,

    var firstname: String,

    var lastname: String
) : AbstractDto()
