package com.ereyesalvarez.util

import com.ereyesalvarez.domain.common.exception.EconomyException
import java.time.LocalDate

fun parseLocalDate(input: String?): LocalDate?{
    if(input == null || input == "") return null
    try {
        return LocalDate.parse(input)
    } catch (e: Exception){
        throw EconomyException("Bad input date")
    }
}