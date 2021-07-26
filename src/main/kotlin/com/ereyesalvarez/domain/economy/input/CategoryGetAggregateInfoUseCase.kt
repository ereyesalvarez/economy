package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.CategoryAggregate

interface CategoryGetAggregateInfoUseCase {
    fun execute(filter: FilterCommand = FilterCommand()): List<CategoryAggregate>
}