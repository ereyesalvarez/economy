package com.ereyesalvarez.domain.category.service

import com.ereyesalvarez.domain.category.CategoryAggregate
import com.ereyesalvarez.domain.category.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.category.input.CategoryGetAggregateInfoUseCase
import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.movement.mapper.mapToMovementAggregateList

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