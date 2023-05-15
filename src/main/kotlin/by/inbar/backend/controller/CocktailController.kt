package by.inbar.backend.controller

import by.inbar.backend.dto.model.cocktail.CocktailFull
import by.inbar.backend.dto.model.cocktail.CocktailShort
import by.inbar.backend.dto.model.cocktail.CreateCocktailRequest
import by.inbar.backend.dto.model.cocktail.CreateCocktailResponse
import by.inbar.backend.service.CocktailFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cocktails")
class CocktailController(
    private val cocktailFacade: CocktailFacade
) {
    @PostMapping("/new")
    fun createCocktail(@RequestBody request: CreateCocktailRequest): CreateCocktailResponse {
        return cocktailFacade.createCocktail(request)
    }

    @GetMapping
    fun getCocktails(): List<CocktailShort> {
        return cocktailFacade.getCocktails()
    }

    @GetMapping("/{id}")
    fun getCocktailById(@PathVariable id: Long): CocktailFull {
        return cocktailFacade.getCocktailById(id)
    }
}
