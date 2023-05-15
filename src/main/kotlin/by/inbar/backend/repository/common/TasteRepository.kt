package by.inbar.backend.repository.common

import by.inbar.backend.model.common.Taste
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TasteRepository : JpaRepository<Taste, Int>
