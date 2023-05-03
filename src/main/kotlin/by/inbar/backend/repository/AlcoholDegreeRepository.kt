package by.inbar.backend.repository

import by.inbar.backend.model.composition.AlcoholDegree
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlcoholDegreeRepository : JpaRepository<AlcoholDegree, Int>
