package com.ereyesalvarez.domain.economy.input

interface MovementSetCategoryByIdUseCase {
    fun execute(movementId: String, categoryId: String)
}
