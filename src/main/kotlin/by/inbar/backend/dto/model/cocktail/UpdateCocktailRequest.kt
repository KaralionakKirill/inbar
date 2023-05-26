package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.CookingMethodDto
import by.inbar.backend.dto.model.common.TasteDto
import by.inbar.backend.model.common.Status

data class UpdateCocktailRequest(
    val id: Long,

    val name: String,

    val cookingSteps: String,

    val aboutCocktail: String,

    val imageId: Long,

    val cookingMethod: CookingMethodDto,

    val group: CocktailGroupDto,

    val alcoholDegree: AlcoholDegreeDto,

    val taste: TasteDto,

    val ingredients: List<IngredientDto>,

    val status: Status
) : AbstractDto()
