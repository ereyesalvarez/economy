package com.ereyesalvarez.domain.movement.services

import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.ConceptAggregate
import com.ereyesalvarez.domain.movement.input.ConceptFindAllDistinctUseCase
import com.ereyesalvarez.domain.movement.input.MovementFindAllUseCase

class ConceptFindAllDistinctService(private val movementFindAllUseCase: MovementFindAllUseCase) :
    ConceptFindAllDistinctUseCase {
    override fun execute(filter: FilterCommand): List<ConceptAggregate> {
        val grouped = movementFindAllUseCase.execute(filter)
            .groupBy { it.title }
        return grouped.map {
            ConceptAggregate(
                title = it.key,
                amount = it.value.fold(0.0) { acc, movementAggregate -> acc + movementAggregate.amount },
                count = it.value.count(),
                categories = it.value.distinctBy { movementAggregate -> movementAggregate.categoryId }
                    .mapNotNull { movementAggregate -> movementAggregate.categoryId }
                    .toSet()
            )
        }
    }
}