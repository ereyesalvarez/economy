package com.ereyesalvarez.application.infrastructure.rest

import com.fasterxml.jackson.annotation.JsonCreator

data class CategoryByMovementIdDTO(val movementId: String, val categoryId: String)
data class CategoryByMovementIdListDTO(val movementIdList: List<String>, val categoryId: String)
data class CategoryConceptDTO(val concept: String, val categoryId: String)
data class CategoryCreateDTO @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) constructor(val categoryTitle: String)
data class ErrorDto(val message: String?)