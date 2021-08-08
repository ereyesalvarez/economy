package com.ereyesalvarez.domain.movement.input

interface ConceptSetCategoryByIdUseCase {
    fun execute(title: String, categoryId: String)
}