package com.ereyesalvarez.domain.movement.model

import com.ereyesalvarez.util.generateDomainId
import java.time.LocalDate

data class Transaction(
    val id: String = generateDomainId(),
    val date: LocalDate,
    val concept: String,
    val amount: Double,
    val valueDate: LocalDate? = date,
    val balance: Double?,
    val income: Boolean = false,
    val imported: Boolean = false
)
