package com.ereyesalvarez.domain.movement.input

interface MovementSetCategoryByIdUseCase {
    fun execute(movementId: String, categoryId: String)
}
