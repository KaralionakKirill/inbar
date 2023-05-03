package by.inbar.backend.controller

import by.inbar.backend.service.CompositionFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/compositions")
class CompositionController(
    private val compositionFacade: CompositionFacade
) {
    @GetMapping("/tastes")
    fun getTastes() = compositionFacade.findAllTastes()

    @GetMapping("/primary-ingredients")
    fun getPrimaryIngredients() = compositionFacade.findAllPrimaryIngredients()

    @GetMapping("/alcohol-degrees")
    fun getAlcoholDegrees() = compositionFacade.findAllAlcoholDegrees()

    @GetMapping("/ingredient-types")
    fun getIngredientTypes() = compositionFacade.findAllIngredientTypes()
}
