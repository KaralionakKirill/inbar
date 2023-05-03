package by.inbar.backend.mapper

import by.inbar.backend.dto.model.AlcoholDegreeDto
import by.inbar.backend.dto.model.FileDto
import by.inbar.backend.dto.model.IngredientDto
import by.inbar.backend.dto.model.IngredientTypeDto
import by.inbar.backend.dto.model.PrimaryIngredientDto
import by.inbar.backend.dto.model.TasteDto
import by.inbar.backend.model.File
import by.inbar.backend.model.composition.AlcoholDegree
import by.inbar.backend.model.composition.PrimaryIngredient
import by.inbar.backend.model.composition.Taste
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.model.ingredient.IngredientType

fun AlcoholDegree.toDto() = AlcoholDegreeDto(id, name)

fun PrimaryIngredient.toDto() = PrimaryIngredientDto(id, name)

fun Taste.toDto() = TasteDto(id, name)

fun IngredientType.toDto() = IngredientTypeDto(id, name, ingredients.size)

fun File.toDto() = FileDto(id, name, mimeType, createdTs)

fun Ingredient.toDto() = IngredientDto(
    id,
    name,
    description,
    type.toDto(),
    image.toDto(),
    primaryIngredient.toDto(),
    alcoholDegree.toDto(),
    taste?.toDto(),
    status,
    createdTs,
    modifiedTs
)
