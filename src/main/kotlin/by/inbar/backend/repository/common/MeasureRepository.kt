package by.inbar.backend.repository.common

import by.inbar.backend.model.common.Measure
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MeasureRepository : JpaRepository<Measure, Int>
