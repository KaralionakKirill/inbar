package by.inbar.backend.repository.common

import by.inbar.backend.model.common.CookingMethod
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CookingMethodRepository : JpaRepository<CookingMethod, Int>
