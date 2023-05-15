package by.inbar.backend.mapper

import by.inbar.backend.dto.model.ingredient.IngredientFull
import by.inbar.backend.dto.model.ingredient.IngredientGroupDto
import by.inbar.backend.dto.model.ingredient.IngredientShort
import by.inbar.backend.dto.model.ingredient.IngredientTypeDto
import by.inbar.backend.dto.model.ingredient.PrimaryIngredientDto
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.model.ingredient.IngredientGroup
import by.inbar.backend.model.ingredient.IngredientType
import by.inbar.backend.model.ingredient.PrimaryIngredient

fun PrimaryIngredient.toDto() = PrimaryIngredientDto(id, name)

fun PrimaryIngredientDto.toEntity() = PrimaryIngredient(name).apply { this.id = this@toEntity.id }

fun IngredientGroup.toDto() = IngredientGroupDto(
    id,
    name,
    cocktailBaseName,
    cocktailBase,
    instrument
)

fun IngredientGroupDto.toEntity() = IngredientGroup(
    name,
    cocktailBaseName,
    cocktailBase,
    instrument
).apply { this.id = this@toEntity.id }

fun IngredientType.toDto() = IngredientTypeDto(id, name, imageName, ingredients.size)

fun IngredientTypeDto.toEntity() = IngredientType(name, imageName).apply { this.id = this@toEntity.id }

fun Ingredient.toShort() = IngredientShort(
    id,
    name,
    image.id,
    type.toDto(),
    status,
    createdTs,
    modifiedTs
)

fun Ingredient.toFull() = IngredientFull(
    this.toShort(),
    description,
    primaryIngredient.toDto(),
    group.toDto(),
    alcoholDegree.toDto(),
    taste.toDto(),
    this.cocktails.map { it.cocktail.toShort() }
)

