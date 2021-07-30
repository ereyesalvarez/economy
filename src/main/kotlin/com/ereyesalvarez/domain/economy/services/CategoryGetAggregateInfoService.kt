package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.CategoryAggregate
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.CategoryGetAggregateInfoUseCase
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.economy.mapper.mapToMovementAggregateList

class CategoryGetAggregateInfoService(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryFindAllUseCase: CategoryFindAllUseCase
) :
    CategoryGetAggregateInfoUseCase {
    override fun execute(filter: FilterCommand): List<CategoryAggregate> {
        val categories = categoryFindAllUseCase.execute()
        return mapToMovementAggregateList(movementFindAllUseCase.execute(filter), categories)
    }
}