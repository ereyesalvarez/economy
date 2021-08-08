package com.ereyesalvarez.domain.movement.services

import com.ereyesalvarez.domain.movement.input.MovementSetCategoryByIdUseCase
import com.ereyesalvarez.domain.movement.output.MovementPersistentPort

class MovementSetCategoryByIdService(private val movementPersistentPort: MovementPersistentPort) :
    MovementSetCategoryByIdUseCase {
    override fun execute(movementId: String, categoryId: String) {
        movementPersistentPort.updateCategoryIdByMovementId(movementId, categoryId)
    }
}