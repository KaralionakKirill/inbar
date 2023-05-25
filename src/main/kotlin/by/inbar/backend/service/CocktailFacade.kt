package by.inbar.backend.service

import by.inbar.backend.dto.model.cocktail.CocktailFull
import by.inbar.backend.dto.model.cocktail.CocktailShort
import by.inbar.backend.dto.model.cocktail.CreateCocktailRequest
import by.inbar.backend.dto.model.cocktail.CreateCocktailResponse
import by.inbar.backend.dto.model.cocktail.IngredientDto
import by.inbar.backend.mapper.toEntity
import by.inbar.backend.mapper.toFull
import by.inbar.backend.mapper.toShort
import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.cocktail.CocktailIngredient
import by.inbar.backend.model.common.Status
import by.inbar.backend.model.user.Role
import by.inbar.backend.service.model.CocktailIngredientService
import by.inbar.backend.service.model.CocktailService
import by.inbar.backend.service.model.FileService
import by.inbar.backend.service.model.IngredientService
import by.inbar.backend.service.model.UserService
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class CocktailFacade(
    private val cocktailIngredientService: CocktailIngredientService,

    private val cocktailService: CocktailService,

    private val ingredientService: IngredientService,

    private val userService: UserService,

    private val fileService: FileService
) {
    fun createCocktail(request: CreateCocktailRequest): CreateCocktailResponse = with(request) {
        val image = fileService.getById(imageId)
        val user = userService.findByEmail(author)
            .orElseThrow { UsernameNotFoundException("User not found") }

        val author = if (user.role == Role.ADMIN) null else user
        val status = if (user.role == Role.ADMIN) Status.REVIEWED else Status.PENDING
        val cocktail = cocktailService.save(
            Cocktail(
                name,
                cookingSteps,
                aboutCocktail,
                image,
                cookingMethod.toEntity(),
                group.toEntity(),
                alcoholDegree.toEntity(),
                taste.toEntity(),
                author,
                status
            )
        )
        cocktailIngredientService.saveAll(ingredients.toCocktailIngredient(cocktail))
        CreateCocktailResponse(cocktail.id, cocktail.name)
    }

    fun getCocktails(): List<CocktailShort> {
        return cocktailService.findAll().map { it.toShort() }
    }

    fun getCocktailById(id: Long): CocktailFull {
        return cocktailService.getById(id).toFull()
    }

    fun likeByUser(id: Long, username: String): CocktailShort {
        val user = userService.findByEmail(username)
            .orElseThrow { UsernameNotFoundException("User not found") }
        val cocktail = cocktailService.getById(id)

        if (user.likedCocktails.contains(cocktail)) {
            cocktail.likedByUsers.remove(user)
            user.likedCocktails.remove(cocktail)
        } else {
            cocktail.likedByUsers.add(user)
            user.likedCocktails.add(cocktail)
        }
        userService.save(user)
        return cocktailService.save(cocktail).toShort()
    }

    private fun List<IngredientDto>.toCocktailIngredient(cocktail: Cocktail) = map {
        val ingredient = ingredientService.getById(it.ingredient.id)
        CocktailIngredient(
            it.value,
            cocktail,
            ingredient,
            it.measure.toEntity()
        )
    }

    fun getMostRatedCocktails(): List<CocktailShort> {
        return cocktailService.findAll().map { it to (it.averageRating + it.likedByUsers.size) }
            .sortedBy { it.second }
            .map { it.first }
            .map { it.toShort() }
    }

    fun getFrequentlyLikedCocktails(): List<CocktailShort> {
        return cocktailService.findAll().sortedBy { it.likedByUsers.size }
            .map { it.toShort() }
    }
}
