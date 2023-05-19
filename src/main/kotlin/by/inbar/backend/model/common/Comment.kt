package by.inbar.backend.model.common

import by.inbar.backend.model.cocktail.Cocktail
import by.inbar.backend.model.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.Instant

@Entity
class Comment(
    @Column(nullable = false)
    var rating: Int,

    @Column(nullable = false)
    var message: String,

    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    var cocktail: Cocktail,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var author: User,

    @Column(nullable = false)
    var createdTs: Instant = Instant.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}
