package com.ereyesalvarez.domain.movement

import java.time.LocalDate


data class MovementAggregate(
    val id: String,
    val date: LocalDate,
    val title: String,
    val amount: Double,
    val categoryId: String? = null
)

data class ConceptAggregate(
    val title: String,
    val amount: Double,
    val count: Int = 0,
    val categories: Set<String>
)
