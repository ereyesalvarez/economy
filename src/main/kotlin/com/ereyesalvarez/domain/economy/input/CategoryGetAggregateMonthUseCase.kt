package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.CategoryExpenseByMonth

interface CategoryGetAggregateMonthUseCase {
    fun execute(filter: FilterCommand): List<CategoryExpenseByMonth>
}