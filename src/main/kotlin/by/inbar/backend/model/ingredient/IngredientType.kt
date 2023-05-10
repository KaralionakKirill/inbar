package by.inbar.backend.model.ingredient

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class IngredientType(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var imageName: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @OneToMany(mappedBy = "type")
    var ingredients: MutableList<Ingredient> = mutableListOf()
}
