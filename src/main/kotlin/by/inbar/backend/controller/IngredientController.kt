package by.inbar.backend.controller

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.dto.model.ingredient.CreateIngredientRequest
import by.inbar.backend.dto.model.ingredient.CreateIngredientResponse
import by.inbar.backend.dto.model.ingredient.IngredientFull
import by.inbar.backend.dto.model.ingredient.IngredientShort
import by.inbar.backend.dto.model.ingredient.UpdateIngredientRequest
import by.inbar.backend.dto.model.ingredient.UpdateIngredientResponse
import by.inbar.backend.service.IngredientFacade
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ingredients")
class IngredientController(
    private val ingredientFacade: IngredientFacade
) {
    @PostMapping("/new")
    fun createIngredient(@RequestBody request: CreateIngredientRequest): CreateIngredientResponse {
        return ingredientFacade.createIngredient(request)
    }

    @PutMapping("/update")
    fun updateIngredient(@RequestBody request: UpdateIngredientRequest): UpdateIngredientResponse {
        return ingredientFacade.updateIngredient(request)
    }

    @GetMapping
    fun getIngredients(): List<IngredientShort> {
        return ingredientFacade.getIngredients()
    }

    @GetMapping("/{id}")
    fun getIngredientById(@PathVariable id: Long): IngredientFull {
        return ingredientFacade.getIngredientById(id)
    }

    @PostMapping
    fun getIngredientsByFilter(@RequestBody filter: Filter): Page<IngredientShort> {
        return ingredientFacade.findAllByFilter(filter)
    }
}
