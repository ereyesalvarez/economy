
package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.MovementAggregate

interface MovementFindAllUseCase {
    fun execute(): List<MovementAggregate>
}