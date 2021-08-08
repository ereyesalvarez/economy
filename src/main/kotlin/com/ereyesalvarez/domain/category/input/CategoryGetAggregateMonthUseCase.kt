package com.ereyesalvarez.domain.category.input

import com.ereyesalvarez.domain.category.CategoryExpenseByMonth
import com.ereyesalvarez.domain.common.input.command.FilterCommand


interface CategoryGetAggregateMonthUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<CategoryExpenseByMonth>
}