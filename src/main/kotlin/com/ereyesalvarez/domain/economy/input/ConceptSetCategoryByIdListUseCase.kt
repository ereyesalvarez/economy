package com.ereyesalvarez.domain.economy.input

interface ConceptSetCategoryByIdListUseCase {
    fun execute(ids: List<String>, categoryId: String)
}