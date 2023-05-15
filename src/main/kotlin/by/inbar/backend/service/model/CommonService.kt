package by.inbar.backend.service.model

import by.inbar.backend.model.cocktail.CocktailGroup
import by.inbar.backend.model.common.AlcoholDegree
import by.inbar.backend.model.common.CookingMethod
import by.inbar.backend.model.common.Measure
import by.inbar.backend.model.common.Taste
import by.inbar.backend.model.ingredient.IngredientGroup
import by.inbar.backend.model.ingredient.IngredientType
import by.inbar.backend.model.ingredient.PrimaryIngredient
import by.inbar.backend.repository.cocktail.CocktailGroupRepository
import by.inbar.backend.repository.common.AlcoholDegreeRepository
import by.inbar.backend.repository.common.CookingMethodRepository
import by.inbar.backend.repository.common.MeasureRepository
import by.inbar.backend.repository.common.TasteRepository
import by.inbar.backend.repository.ingredient.IngredientGroupRepository
import by.inbar.backend.repository.ingredient.IngredientTypeRepository
import by.inbar.backend.repository.ingredient.PrimaryIngredientRepository
import org.springframework.stereotype.Service

@Service
class CommonService(
    private val measureRepository: MeasureRepository,

    private val cookingMethodRepository: CookingMethodRepository,

    private val tasteRepository: TasteRepository,

    private val alcoholDegreeRepository: AlcoholDegreeRepository,

    private val primaryIngredientRepository: PrimaryIngredientRepository,

    private val ingredientGroupRepository: IngredientGroupRepository,

    private val ingredientTypeRepository: IngredientTypeRepository,

    private val cocktailGroupRepository: CocktailGroupRepository
) {
    fun findAllTastes(): List<Taste> = tasteRepository.findAll()

    fun findAllAlcoholDegrees(): List<AlcoholDegree> = alcoholDegreeRepository.findAll()

    fun findAllPrimaryIngredients(): List<PrimaryIngredient> = primaryIngredientRepository.findAll()

    fun findAllIngredientGroups(): List<IngredientGroup> = ingredientGroupRepository.findAll()

    fun findAllIngredientTypes(): List<IngredientType> = ingredientTypeRepository.findAll()

    fun findAllMeasure(): List<Measure> = measureRepository.findAll()

    fun findAllCookingMethods(): List<CookingMethod> = cookingMethodRepository.findAll()

    fun findAllCocktailGroups(): List<CocktailGroup> = cocktailGroupRepository.findAll()
}
