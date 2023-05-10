package by.inbar.backend.dto.model

import by.inbar.backend.model.Status
import java.time.Instant

open class IngredientFull(
    id: Long,

    name: String,

    imageId: Long,

    type: IngredientTypeDto,

    var description: String,

    var primaryIngredient: PrimaryIngredientDto,

    var alcoholDegree: AlcoholDegreeDto,

    var taste: TasteDto?,

    var status: Status,

    var createdTs: Instant,

    var modifiedTs: Instant
) : IngredientShort(
    id,
    name,
    imageId,
    type
) {
    constructor(
        short: IngredientShort,
        description: String,
        primaryIngredient: PrimaryIngredientDto,
        alcoholDegree: AlcoholDegreeDto,
        taste: TasteDto?,
        status: Status,
        createdTs: Instant,
        modifiedTs: Instant
    ) : this(
        short.id,
        short.name,
        short.imageId,
        short.type,
        description,
        primaryIngredient,
        alcoholDegree,
        taste,
        status,
        createdTs,
        modifiedTs
    )
}
