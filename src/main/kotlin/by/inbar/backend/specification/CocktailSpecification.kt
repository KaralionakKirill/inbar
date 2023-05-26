package by.inbar.backend.specification

import by.inbar.backend.dto.filter.LazyLoadEvent
import by.inbar.backend.model.cocktail.Cocktail

class CocktailSpecification(
    lazyLoadEvent: LazyLoadEvent
) : AbstractSpecification<Cocktail>(lazyLoadEvent)
