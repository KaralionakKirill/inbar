package by.inbar.backend.mapper

import by.inbar.backend.dto.model.AlcoholDegreeDto
import by.inbar.backend.dto.model.FileDto
import by.inbar.backend.dto.model.IngredientFull
import by.inbar.backend.dto.model.IngredientShort
import by.inbar.backend.dto.model.IngredientTypeDto
import by.inbar.backend.dto.model.PrimaryIngredientDto
import by.inbar.backend.dto.model.TasteDto
import by.inbar.backend.dto.model.UserInfo
import by.inbar.backend.model.File
import by.inbar.backend.model.composition.AlcoholDegree
import by.inbar.backend.model.composition.PrimaryIngredient
import by.inbar.backend.model.composition.Taste
import by.inbar.backend.model.ingredient.Ingredient
import by.inbar.backend.model.ingredient.IngredientType
import by.inbar.backend.model.user.User

fun AlcoholDegree.toDto() = AlcoholDegreeDto(id, name)

fun PrimaryIngredient.toDto() = PrimaryIngredientDto(id, name)

fun Taste.toDto() = TasteDto(id, name)

fun IngredientType.toDto() = IngredientTypeDto(id, name, imageName, ingredients.size)

fun File.toDto() = FileDto(id, name, mimeType, createdTs)

fun Ingredient.toShort() = IngredientShort(
    id,
    name,
    image.id,
    type.toDto(),
)

fun Ingredient.toFull() = IngredientFull(
    this.toShort(),
    description,
    primaryIngredient.toDto(),
    alcoholDegree.toDto(),
    taste?.toDto(),
    status,
    createdTs,
    modifiedTs
)

fun User.toDto() = UserInfo(
    firstname,
    lastname,
    email,
    avatar?.id,
    aboutMe,
    role
)
