package by.inbar.backend.model.ingredient

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class IngredientGroup(
    @Column(nullable = false)
    var name: String,

    var cocktailBaseName: String?,

    @Column(nullable = false)
    var cocktailBase: Boolean,

    @Column(nullable = false)
    var instrument: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @OneToMany(mappedBy = "group")
    var ingredients: MutableList<Ingredient> = mutableListOf()
}
