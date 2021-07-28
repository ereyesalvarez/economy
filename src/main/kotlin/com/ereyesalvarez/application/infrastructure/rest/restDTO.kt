package com.ereyesalvarez.application.infrastructure.rest

data class CategoryByMovementIdDTO(val movementId: String, val categoryId: String)
data class CategoryByMovementIdListDTO(val movementIdList: List<String>, val categoryId: String)
data class CategoryConceptDTO(val concept: String, val categoryId: String)
data class CategoryCreateDTO (
    val categoryTitle: String,
    val categoryGroup: String
)
data class ErrorDto(val message: String?)