package by.inbar.backend.model.user

import by.inbar.backend.model.token.Token
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

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
    var role: Role
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tokens = mutableListOf<Token>()
}
