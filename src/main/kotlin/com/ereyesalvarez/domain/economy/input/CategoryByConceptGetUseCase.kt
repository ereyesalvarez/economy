package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.Category

interface CategoryByConceptGetUseCase {
    fun execute(concept: String): Category?
}
