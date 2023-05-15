package by.inbar.backend.model.cocktail

import by.inbar.backend.model.common.Measure
import by.inbar.backend.model.ingredient.Ingredient
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class CocktailIngredient(
    @Column(nullable = false)
    var value: Int,

    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    var cocktail: Cocktail,

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    var ingredient: Ingredient,

    @ManyToOne
    @JoinColumn(name = "measure_id", nullable = false)
    var measure: Measure
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}
