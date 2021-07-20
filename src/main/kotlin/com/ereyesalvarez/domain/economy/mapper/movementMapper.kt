package com.ereyesalvarez.domain.economy.mapper

import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.MovementAggregate
import com.ereyesalvarez.domain.economy.Transaction

fun Movement.toMovementAggregate(): MovementAggregate {
    val totalAmount = transactions.fold(0.0) { acc, transaction -> acc + transaction.amount }
    return MovementAggregate(
        id,
        date,
        title,
        totalAmount,
        category
    )
}