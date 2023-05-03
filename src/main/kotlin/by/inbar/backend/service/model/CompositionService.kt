package by.inbar.backend.service.model

import by.inbar.backend.model.composition.AlcoholDegree
import by.inbar.backend.model.composition.PrimaryIngredient
import by.inbar.backend.model.composition.Taste
import by.inbar.backend.model.ingredient.IngredientType
import by.inbar.backend.repository.AlcoholDegreeRepository
import by.inbar.backend.repository.IngredientTypeRepository
import by.inbar.backend.repository.PrimaryIngredientRepository
import by.inbar.backend.repository.TasteRepository
import org.springframework.stereotype.Service

@Service
class CompositionService(
    private val tasteRepository: TasteRepository,

    private val alcoholDegreeRepository: AlcoholDegreeRepository,

    private val primaryIngredientRepository: PrimaryIngredientRepository,

    private val ingredientTypeRepository: IngredientTypeRepository
) {
    fun findAllTastes(): List<Taste> = tasteRepository.findAll()

    fun findAllAlcoholDegrees(): List<AlcoholDegree> = alcoholDegreeRepository.findAll()


    fun findAllPrimaryIngredients(): List<PrimaryIngredient> = primaryIngredientRepository.findAll()

    fun findAllIngredientTypes(): List<IngredientType> = ingredientTypeRepository.findAll()

}
