package com.ereyesalvarez.domain.category.input

import com.ereyesalvarez.domain.category.CategoryMonthAggregate
import com.ereyesalvarez.domain.common.input.command.FilterCommand

interface CategoryGetAggregateMonthUseByCategoryUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<CategoryMonthAggregate>
}