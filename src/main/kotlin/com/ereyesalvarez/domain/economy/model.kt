package com.ereyesalvarez.domain.economy

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val date: LocalDate,
    val valueDate: LocalDate = date,
    val concept: String,
    val amount: Double,
    val balance: Double,
)

data class Movement(
    val id: String,
    val date: LocalDate,
    val title: String,
    val category: String? = null,
    val transactions: Set<Transaction> = setOf()
)

data class Category(
    val id: String,
    val title: String
)