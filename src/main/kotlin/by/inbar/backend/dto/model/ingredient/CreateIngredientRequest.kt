package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.TasteDto

data class CreateIngredientRequest(
    val name: String,

    val description: String,

    val imageId: Long,

    val type: IngredientTypeDto,

    val primaryIngredient: PrimaryIngredientDto,

    val group: IngredientGroupDto,

    val alcoholDegree: AlcoholDegreeDto,

    val taste: TasteDto
) : AbstractDto()
