package com.ereyesalvarez.domain.economy

import java.time.LocalDate


data class MovementAggregate(
    val id: String,
    val date: LocalDate,
    val title: String,
    val amount: Double,
    val category: String? = null,
)

data class ConceptAggregate(
    val title: String,
    val amount: String,
    val categories: Set<String>
)