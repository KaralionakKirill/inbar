package by.inbar.backend.controller

import by.inbar.backend.service.CommonFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/common")
class CommonController(
    private val commonFacade: CommonFacade
) {
    @GetMapping("/tastes")
    fun getTastes() = commonFacade.findAllTastes()

    @GetMapping("/primary-ingredients")
    fun getPrimaryIngredients() = commonFacade.findAllPrimaryIngredients()

    @GetMapping("/alcohol-degrees")
    fun getAlcoholDegrees() = commonFacade.findAllAlcoholDegrees()

    @GetMapping("/ingredient-types")
    fun getIngredientTypes() = commonFacade.findAllIngredientTypes()

    @GetMapping("/ingredient-groups")
    fun getIngredientGroups() = commonFacade.findAllIngredientGroups()

    @GetMapping("/cooking-methods")
    fun getCookingMethods() = commonFacade.findAllCookingMethods()

    @GetMapping("/measure")
    fun getMeasure() = commonFacade.findAllMeasure()

    @GetMapping("/cocktail-groups")
    fun getCocktailGroups() = commonFacade.findAllCocktailGroups()
}
