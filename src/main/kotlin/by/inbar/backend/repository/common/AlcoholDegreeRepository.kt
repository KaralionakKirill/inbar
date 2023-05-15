package by.inbar.backend.repository.common

import by.inbar.backend.model.common.AlcoholDegree
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlcoholDegreeRepository : JpaRepository<AlcoholDegree, Int>
