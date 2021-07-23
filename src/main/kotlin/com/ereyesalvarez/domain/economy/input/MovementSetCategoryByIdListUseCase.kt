package com.ereyesalvarez.domain.economy.input

interface MovementSetCategoryByIdListUseCase {
    fun execute(ids: List<String>, categoryId: String)
}