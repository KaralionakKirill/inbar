package by.inbar.backend.mapper

import by.inbar.backend.dto.model.cocktail.CocktailFull
import by.inbar.backend.dto.model.cocktail.CocktailGroupDto
import by.inbar.backend.dto.model.cocktail.CocktailShort
import by.inbar.backend.dto.model.cocktail.IngredientDto
import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.cocktail.CocktailGroup
import by.inbar.backend.model.cocktail.CocktailIngredient

fun CocktailGroup.toDto() = CocktailGroupDto(id, name)

fun CocktailGroupDto.toEntity() = CocktailGroup(name).apply { this.id = this@toEntity.id }

fun CocktailIngredient.toIngredientDto() = IngredientDto(id, value, ingredient.toShort(), measure.toDto())

fun Cocktail.toShort() = CocktailShort(
    id,
    name,
    image.id,
    author?.toDto(),
    status,
    createdTs,
    modifiedTs
)

fun Cocktail.toFull() = CocktailFull(
    this.toShort(),
    cookingSteps,
    aboutCocktail,
    taste.toDto(),
    alcoholDegree.toDto(),
    cookingMethod.toDto(),
    cocktailGroup.toDto(),
    ingredients.map { it.toIngredientDto() }
)
