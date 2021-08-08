package com.ereyesalvarez.domain.category.input

import com.ereyesalvarez.domain.category.CategoryExpenseByMonth
import com.ereyesalvarez.domain.category.CategoryGroupMonthAggregate
import com.ereyesalvarez.domain.common.input.command.FilterCommand

interface CategoryGetGroupedByGroupMonthlyUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<CategoryGroupMonthAggregate>
}