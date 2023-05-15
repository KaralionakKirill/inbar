package by.inbar.backend.service

import by.inbar.backend.dto.model.cocktail.CocktailGroupDto
import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.CookingMethodDto
import by.inbar.backend.dto.model.common.MeasureDto
import by.inbar.backend.dto.model.common.TasteDto
import by.inbar.backend.dto.model.ingredient.IngredientGroupDto
import by.inbar.backend.dto.model.ingredient.IngredientTypeDto
import by.inbar.backend.dto.model.ingredient.PrimaryIngredientDto
import by.inbar.backend.mapper.toDto
import by.inbar.backend.service.model.CommonService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CommonFacade(
    private val commonService: CommonService
) {
    fun findAllTastes(): List<TasteDto> = commonService.findAllTastes()
        .map { it.toDto() }

    fun findAllAlcoholDegrees(): List<AlcoholDegreeDto> = commonService.findAllAlcoholDegrees()
        .map { it.toDto() }

    fun findAllPrimaryIngredients(): List<PrimaryIngredientDto> = commonService.findAllPrimaryIngredients()
        .map { it.toDto() }

    fun findAllIngredientTypes(): List<IngredientTypeDto> = commonService.findAllIngredientTypes()
        .map { it.toDto() }

    fun findAllIngredientGroups(): List<IngredientGroupDto> = commonService.findAllIngredientGroups()
        .map { it.toDto() }

    fun findAllMeasure(): List<MeasureDto> = commonService.findAllMeasure()
        .map { it.toDto() }

    fun findAllCookingMethods(): List<CookingMethodDto> = commonService.findAllCookingMethods()
        .map { it.toDto() }

    fun findAllCocktailGroups(): List<CocktailGroupDto> = commonService.findAllCocktailGroups()
        .map { it.toDto() }
}
