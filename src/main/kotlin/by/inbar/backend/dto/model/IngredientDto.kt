package by.inbar.backend.dto.model

import by.inbar.backend.dto.AbstractDto
import by.inbar.backend.model.Status
import java.time.Instant

data class IngredientDto(
    var id: Long,

    var name: String,

    var description: String,

    var type: IngredientTypeDto,

    var image: FileDto,

    var primaryIngredient: PrimaryIngredientDto,

    var alcoholDegree: AlcoholDegreeDto,

    var taste: TasteDto?,

    var status: Status,

    var createdTs: Instant,

    var modifiedTs: Instant
) : AbstractDto()
