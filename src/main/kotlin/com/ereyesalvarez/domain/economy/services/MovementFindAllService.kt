package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.MovementAggregate
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.economy.mapper.toMovementAggregate
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

class MovementFindAllService(
    private val movementPersistentPort: MovementPersistentPort
) : MovementFindAllUseCase {
    override fun execute(): List<MovementAggregate> {
        return movementPersistentPort.findAll().map { it.toMovementAggregate() }
    }
}