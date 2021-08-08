package com.ereyesalvarez.domain.movement.input

import java.time.LocalDate

data class TransactionImportCommand(
    val date: LocalDate,
    val concept: String,
    val amount: Double,
    val valueDate: LocalDate = date,
    val balance: Double?,
)
