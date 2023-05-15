package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.CookingMethodDto
import by.inbar.backend.dto.model.common.TasteDto
import by.inbar.backend.dto.model.user.UserInfo
import by.inbar.backend.model.Status
import java.time.Instant

open class CocktailFull(
    id: Long,

    name: String,

    imageId: Long,

    author: UserInfo?,

    status: Status,

    createdTs: Instant,

    modifiedTs: Instant,

    var cookingSteps: String,

    var aboutCocktail: String,

    var tasteDto: TasteDto,

    var alcoholDegreeDto: AlcoholDegreeDto,

    var cookingMethod: CookingMethodDto,

    var cocktailGroup: CocktailGroupDto,

    var ingredients: List<IngredientDto>
) : CocktailShort(
    id,
    name,
    imageId,
    author,
    status,
    createdTs,
    modifiedTs,
) {
    constructor(
        short: CocktailShort,
        cookingSteps: String,
        aboutCocktail: String,
        tasteDto: TasteDto,
        alcoholDegreeDto: AlcoholDegreeDto,
        cookingMethod: CookingMethodDto,
        cocktailGroup: CocktailGroupDto,
        ingredients: List<IngredientDto>
    ) : this(
        short.id,
        short.name,
        short.imageId,
        short.author,
        short.status,
        short.createdTs,
        short.modifiedTs,
        cookingSteps,
        aboutCocktail,
        tasteDto,
        alcoholDegreeDto,
        cookingMethod,
        cocktailGroup,
        ingredients
    )
}
