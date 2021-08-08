package com.ereyesalvarez.domain.movement.mapper

import com.ereyesalvarez.domain.movement.MovementAggregate
import com.ereyesalvarez.domain.movement.model.Movement

fun Movement.toMovementAggregate(): MovementAggregate {
    return MovementAggregate(
        id,
        date,
        title,
        transactionSum(),
        categoryId
    )
}