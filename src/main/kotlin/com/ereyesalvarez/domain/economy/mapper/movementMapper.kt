package com.ereyesalvarez.domain.economy.mapper

import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.MovementAggregate

fun Movement.toMovementAggregate(): MovementAggregate {
    return MovementAggregate(
        id,
        date,
        title,
        transactionSum(),
        categoryId
    )
}