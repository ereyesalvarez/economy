package com.ereyesalvarez.domain.economy.input

import com.ereyesalvarez.domain.economy.CategoryAggregate

interface CategoryGetAggregateInfoUseCase {
    fun execute(): List<CategoryAggregate>
}