package by.inbar.backend.dto.model.cocktail

import by.inbar.backend.dto.model.user.UserInfo
import by.inbar.backend.model.Status
import java.time.Instant

open class CocktailShort(
    var id: Long,

    var name: String,

    var imageId: Long,

    var author: UserInfo?,

    var status: Status,

    var createdTs: Instant,

    var modifiedTs: Instant
)
