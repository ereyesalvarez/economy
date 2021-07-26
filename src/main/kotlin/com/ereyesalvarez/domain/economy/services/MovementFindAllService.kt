package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.MovementAggregate
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.economy.mapper.toMovementAggregate
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

class MovementFindAllService(
    private val movementPersistentPort: MovementPersistentPort
) : MovementFindAllUseCase {
    override fun execute(filter: FilterCommand): List<MovementAggregate> {
        if (filter.expenses) {
            return queryWithFilters(filter).filter { it.transactionSum() > 0 }.map { it.toMovementAggregate() }
        }
        return queryWithFilters(filter).map { it.toMovementAggregate() }
    }

    private fun queryWithFilters(filter: FilterCommand): List<Movement> {
        if (filter.startDate == null && filter.endDate == null) {
            return movementPersistentPort.findAll()
        }
        if (filter.startDate == null) {
            return movementPersistentPort.findAll().filter { it.date.isBefore(filter.endDate) || it.date.isEqual(filter.endDate) }
        }
        if (filter.endDate == null){
            return movementPersistentPort.findAll().filter { it.date.isAfter(filter.startDate) || it.date.isEqual(filter.startDate) }
        }
        return movementPersistentPort.findAll()
            .filter { (it.date.isEqual(filter.endDate) || it.date.isEqual(filter.startDate)) ||
                    (it.date.isAfter(filter.startDate) && it.date.isBefore(filter.endDate))}
    }
}