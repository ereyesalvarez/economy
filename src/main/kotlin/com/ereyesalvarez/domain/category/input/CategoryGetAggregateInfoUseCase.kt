package com.ereyesalvarez.domain.category.input

import com.ereyesalvarez.domain.category.CategoryAggregate
import com.ereyesalvarez.domain.common.input.command.FilterCommand

interface CategoryGetAggregateInfoUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<CategoryAggregate>
}