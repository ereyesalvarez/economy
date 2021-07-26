package com.ereyesalvarez.domain.economy.input

import java.time.LocalDate

data class TransactionImportCommand(
    val date: LocalDate,
    val concept: String,
    val amount: Double,
    val valueDate: LocalDate = date,
    val balance: Double?,
)

data class FilterCommand(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val expenses:Boolean = true,
)