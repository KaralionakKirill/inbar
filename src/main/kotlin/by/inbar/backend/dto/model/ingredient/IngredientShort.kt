package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.model.common.Status
import java.time.Instant

open class IngredientShort(
    val id: Long,

    val name: String,

    val imageId: Long,

    val type: IngredientTypeDto,

    var group: IngredientGroupDto,

    var status: Status,

    var createdTs: Instant,

    var modifiedTs: Instant
)
