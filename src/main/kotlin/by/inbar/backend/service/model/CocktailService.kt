package by.inbar.backend.service.model

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.exception.NotFoundException
import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.repository.cocktail.CocktailRepository
import by.inbar.backend.specification.CocktailSpecification
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CocktailService(
    private val cocktailRepository: CocktailRepository
) {
    fun save(cocktail: Cocktail): Cocktail {
        return cocktailRepository.save(cocktail)
    }

    fun findAll(): MutableList<Cocktail> {
        return cocktailRepository.findAll()
    }

    fun getById(id: Long): Cocktail {
        return cocktailRepository.findById(id)
            .orElseThrow { throw NotFoundException("Cocktail with id=$id not found") }
    }

    fun findAllByFilter(filter: Filter): Page<Cocktail> {
        return cocktailRepository.findAll(CocktailSpecification(filter), filter.toPageRequest())
    }
}
