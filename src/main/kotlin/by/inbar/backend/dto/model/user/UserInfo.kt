package by.inbar.backend.dto.model.user

import by.inbar.backend.dto.model.cocktail.CocktailShort
import by.inbar.backend.model.user.Role
import java.time.Instant

data class UserInfo(
    var id: Long,

    var firstname: String,

    var lastname: String,

    var email: String,

    var avatarId: Long?,

    var aboutMe: String?,

    var role: Role,

    var createdCocktails: List<CocktailShort>,

    var likedCocktails: List<CocktailShort>,

    var commentAmount: Int,

    var createdTs: Instant,

    var modifiedTs: Instant,
)
