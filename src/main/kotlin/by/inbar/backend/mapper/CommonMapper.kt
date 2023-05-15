package by.inbar.backend.mapper

import by.inbar.backend.dto.model.common.AlcoholDegreeDto
import by.inbar.backend.dto.model.common.CookingMethodDto
import by.inbar.backend.dto.model.common.MeasureDto
import by.inbar.backend.dto.model.common.TasteDto
import by.inbar.backend.model.common.AlcoholDegree
import by.inbar.backend.model.common.CookingMethod
import by.inbar.backend.model.common.Measure
import by.inbar.backend.model.common.Taste

fun AlcoholDegree.toDto() = AlcoholDegreeDto(id, name)

fun AlcoholDegreeDto.toEntity() = AlcoholDegree(name).apply { this.id = this@toEntity.id }

fun Taste.toDto() = TasteDto(id, name)

fun TasteDto.toEntity() = Taste(name).apply { this.id = this@toEntity.id }

fun Measure.toDto() = MeasureDto(id, name)

fun MeasureDto.toEntity() = Measure(name).apply { this.id = this@toEntity.id }

fun CookingMethod.toDto() = CookingMethodDto(id, name)

fun CookingMethodDto.toEntity() = CookingMethod(name).apply { this.id = this@toEntity.id }
