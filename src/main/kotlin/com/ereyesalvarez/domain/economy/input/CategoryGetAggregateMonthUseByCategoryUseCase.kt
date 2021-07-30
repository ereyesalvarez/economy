package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.CategoryExpenseByMonth
import com.ereyesalvarez.domain.economy.CategoryMonthAggregate

interface CategoryGetAggregateMonthUseByCategoryUseCase {
    fun execute(filter: FilterCommand): List<CategoryMonthAggregate>
}