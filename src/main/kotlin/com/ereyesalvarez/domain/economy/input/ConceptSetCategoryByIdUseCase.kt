package com.ereyesalvarez.domain.economy.input

interface ConceptSetCategoryByIdUseCase {
    fun execute(concept: String, categoryId: String)
}