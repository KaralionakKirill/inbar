package by.inbar.backend.model.ingredient

import by.inbar.backend.model.cocktail.CocktailIngredient
import by.inbar.backend.model.common.AlcoholDegree
import by.inbar.backend.model.common.File
import by.inbar.backend.model.common.Status
import by.inbar.backend.model.common.Taste
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.time.Instant

@Entity
class Ingredient(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @ManyToOne
    @JoinColumn(name = "ingredient_type_id", nullable = false)
    var type: IngredientType,

    @ManyToOne
    @JoinColumn(name = "ingredient_group_id", nullable = false)
    var group: IngredientGroup,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "file_id", nullable = false)
    var image: File,

    @ManyToOne
    @JoinColumn(name = "primary_ingredient_id", nullable = false)
    var primaryIngredient: PrimaryIngredient,

    @ManyToOne
    @JoinColumn(name = "alcohol_degree_id", nullable = false)
    var alcoholDegree: AlcoholDegree,

    @ManyToOne
    @JoinColumn(name = "taste_id", nullable = false)
    var taste: Taste,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: Status,

    @Column(nullable = false)
    var createdTs: Instant = Instant.now(),

    @Column(nullable = false)
    var modifiedTs: Instant = Instant.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0L

    @OneToMany(mappedBy = "ingredient")
    var cocktails: MutableList<CocktailIngredient> = mutableListOf()
}
