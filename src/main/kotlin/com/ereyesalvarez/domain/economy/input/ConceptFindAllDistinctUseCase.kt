package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.ConceptAggregate

interface ConceptFindAllDistinctUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<ConceptAggregate>
}