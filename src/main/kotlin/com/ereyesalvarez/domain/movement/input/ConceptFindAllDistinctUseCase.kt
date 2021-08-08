package com.ereyesalvarez.domain.movement.input

import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.ConceptAggregate

interface ConceptFindAllDistinctUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<ConceptAggregate>
}