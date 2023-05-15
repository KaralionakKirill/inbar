package by.inbar.backend.model.cocktail

import by.inbar.backend.model.File
import by.inbar.backend.model.Status
import by.inbar.backend.model.common.AlcoholDegree
import by.inbar.backend.model.common.CookingMethod
import by.inbar.backend.model.common.Taste
import by.inbar.backend.model.user.User
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
class Cocktail(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var cookingSteps: String,

    @Column(nullable = false)
    var aboutCocktail: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "file_id", nullable = false)
    var image: File,

    @ManyToOne
    @JoinColumn(name = "cooking_method_id", nullable = false)
    var cookingMethod: CookingMethod,

    @ManyToOne
    @JoinColumn(name = "cocktail_group_id", nullable = false)
    var cocktailGroup: CocktailGroup,

    @ManyToOne
    @JoinColumn(name = "alcohol_degree_id", nullable = false)
    var alcoholDegree: AlcoholDegree,

    @ManyToOne
    @JoinColumn(name = "taste_id", nullable = false)
    var taste: Taste,

    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: User? = null,

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
    var id : Long = 0L

    @OneToMany(mappedBy = "cocktail", cascade = [CascadeType.ALL])
    var ingredients: MutableList<CocktailIngredient> = mutableListOf()
}
