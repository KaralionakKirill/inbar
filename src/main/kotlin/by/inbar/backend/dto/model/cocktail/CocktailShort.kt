package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.model.common.Status
import java.time.Instant

open class CocktailShort(
    var id: Long,

    var name: String,

    var imageId: Long,

    var author: CocktailAuthor?,

    var group: CocktailGroupDto,

    var likesAmount: Int,

    var status: Status,

    var createdTs: Instant,

    var modifiedTs: Instant
)
