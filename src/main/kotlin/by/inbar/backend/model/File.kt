package by.inbar.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.Instant

@Entity
class File(
    @Column(nullable = false)
    var data: ByteArray,

    @Column(nullable = false)
    var mimeType: String,

    var name: String? = null,

    @Column(nullable = false)
    var createdTs: Instant = Instant.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0L
}
