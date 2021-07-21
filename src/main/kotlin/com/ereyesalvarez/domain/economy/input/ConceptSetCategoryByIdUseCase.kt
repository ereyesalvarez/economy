package com.ereyesalvarez.domain.economy.input

interface ConceptSetCategoryByIdUseCase {
    fun execute(title: String, categoryId: String)
}