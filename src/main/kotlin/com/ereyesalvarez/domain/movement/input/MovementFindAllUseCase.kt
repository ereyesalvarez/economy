package com.ereyesalvarez.domain.movement.input

import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.MovementAggregate

interface MovementFindAllUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<MovementAggregate>
}