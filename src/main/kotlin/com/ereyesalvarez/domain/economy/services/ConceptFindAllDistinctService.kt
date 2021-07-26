package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.ConceptAggregate
import com.ereyesalvarez.domain.economy.input.ConceptFindAllDistinctUseCase
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase

class ConceptFindAllDistinctService(private val movementFindAllUseCase: MovementFindAllUseCase) :
    ConceptFindAllDistinctUseCase {
    override fun execute(): List<ConceptAggregate> {
        val grouped = movementFindAllUseCase.execute()
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