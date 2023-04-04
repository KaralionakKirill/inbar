package by.inbar.backend.model.token

import by.inbar.backend.model.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Token(
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @Column(nullable = false)
    var token: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var tokenType: TokenType = TokenType.BEARER,

    var revoked: Boolean = false,

    var expired: Boolean = false
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}
