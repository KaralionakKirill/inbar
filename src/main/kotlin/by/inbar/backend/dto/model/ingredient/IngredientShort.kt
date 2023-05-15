package by.inbar.backend.dto.model.ingredient

import by.inbar.backend.model.Status
import java.time.Instant

open class IngredientShort(
    val id: Long,

    val name: String,

    val imageId: Long,

    val type: IngredientTypeDto,

    var status: Status,

    var createdTs: Instant,

    var modifiedTs: Instant
)