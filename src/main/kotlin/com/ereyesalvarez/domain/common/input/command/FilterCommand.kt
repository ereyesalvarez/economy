package com.ereyesalvarez.domain.common.input.command

import java.time.LocalDate

data class FilterCommand(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val expenses:Boolean = true,
)