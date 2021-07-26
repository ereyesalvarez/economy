package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdUseCase
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

class MovementSetCategoryByIdService(private val movementPersistentPort: MovementPersistentPort) :
    MovementSetCategoryByIdUseCase {
    override fun execute(movementId: String, categoryId: String) {
        movementPersistentPort.updateCategoryIdByMovementId(movementId, categoryId)
    }
}