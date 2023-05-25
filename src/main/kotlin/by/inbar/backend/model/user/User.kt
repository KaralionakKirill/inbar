package by.inbar.backend.model.user

import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.common.Comment
import by.inbar.backend.model.common.File
import by.inbar.backend.model.token.Token
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var firstname: String,

    @Column(nullable = false)
    var lastname: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "file_id")
    var avatar: File? = null,

    var aboutMe: String? = null,

    @Column(nullable = false)
    var createdTs: Instant = Instant.now(),

    @Column(nullable = false)
    var modifiedTs: Instant = Instant.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tokens = mutableListOf<Token>()

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
    var cocktails = mutableListOf<Cocktail>()

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_cocktail_likes",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "cocktail_id")]
    )
    var likedCocktails = mutableListOf<Cocktail>()
}
