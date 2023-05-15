package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.model.cocktail.CocktailShort
import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.TasteDto
import by.inbar.backend.model.Status
import java.time.Instant

open class IngredientFull(
    id: Long,

    name: String,

    imageId: Long,

    type: IngredientTypeDto,

    status: Status,

    createdTs: Instant,

    modifiedTs: Instant,

    var description: String,

    var primaryIngredient: PrimaryIngredientDto,

    var group: IngredientGroupDto,

    var alcoholDegree: AlcoholDegreeDto,

    var taste: TasteDto,

    var cocktails: List<CocktailShort>
) : IngredientShort(
    id,
    name,
    imageId,
    type,
    status,
    createdTs,
    modifiedTs
) {
    constructor(
        short: IngredientShort,
        description: String,
        primaryIngredient: PrimaryIngredientDto,
        ingredientGroup: IngredientGroupDto,
        alcoholDegree: AlcoholDegreeDto,
        taste: TasteDto,
        cocktails: List<CocktailShort>
    ) : this(
        short.id,
        short.name,
        short.imageId,
        short.type,
        short.status,
        short.createdTs,
        short.modifiedTs,
        description,
        primaryIngredient,
        ingredientGroup,
        alcoholDegree,
        taste,
        cocktails
    )
}
