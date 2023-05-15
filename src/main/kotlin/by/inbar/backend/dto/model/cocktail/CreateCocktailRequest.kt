package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.CookingMethodDto
import by.inbar.backend.dto.model.common.TasteDto

data class CreateCocktailRequest(
    val name: String,

    val cookingSteps: String,

    val aboutCocktail: String,

    val author: String,

    val imageId: Long,

    val cookingMethod: CookingMethodDto,

    val group: CocktailGroupDto,

    val alcoholDegree: AlcoholDegreeDto,

    val taste: TasteDto,

    val ingredients: List<IngredientDto>
) : AbstractDto()
