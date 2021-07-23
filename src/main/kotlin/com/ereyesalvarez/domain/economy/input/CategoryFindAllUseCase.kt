package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.Category

interface CategoryFindAllUseCase {
    fun execute(): List<Category>
}