package by.inbar.backend.repository

import by.inbar.backend.model.composition.Taste
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TasteRepository : JpaRepository<Taste, Int>
