package by.inbar.backend.service

import by.inbar.backend.dto.model.AlcoholDegreeDto
import by.inbar.backend.dto.model.IngredientTypeDto
import by.inbar.backend.dto.model.PrimaryIngredientDto
import by.inbar.backend.dto.model.TasteDto
import by.inbar.backend.mapper.toDto
import by.inbar.backend.service.model.CompositionService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CompositionFacade(
    private val compositionService: CompositionService
) {
    fun findAllTastes(): List<TasteDto> = compositionService.findAllTastes()
        .map { it.toDto() }

    fun findAllAlcoholDegrees(): List<AlcoholDegreeDto> = compositionService.findAllAlcoholDegrees()
        .map { it.toDto() }


    fun findAllPrimaryIngredients(): List<PrimaryIngredientDto> = compositionService.findAllPrimaryIngredients()
        .map { it.toDto() }

    fun findAllIngredientTypes(): List<IngredientTypeDto> = compositionService.findAllIngredientTypes()
        .map { it.toDto() }
}
