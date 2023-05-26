package by.inbar.backend.specification

import by.inbar.backend.dto.filter.LazyLoadEvent
import by.inbar.backend.model.ingredient.Ingredient

class IngredientSpecification(
    lazyLoadEvent: LazyLoadEvent
) : AbstractSpecification<Ingredient>(lazyLoadEvent)
