package com.ereyesalvarez.domain.movement.input

interface MovementSetCategoryByIdListUseCase {
    fun execute(ids: List<String>, categoryId: String)
}