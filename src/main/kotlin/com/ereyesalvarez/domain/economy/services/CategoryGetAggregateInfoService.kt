package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.CategoryAggregate
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.CategoryGetAggregateInfoUseCase
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase

class CategoryGetAggregateInfoService(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryFindAllUseCase: CategoryFindAllUseCase
) :
    CategoryGetAggregateInfoUseCase {
    override fun execute(filter: FilterCommand): List<CategoryAggregate> {
        val categories = categoryFindAllUseCase.execute()
        return movementFindAllUseCase.execute(filter)
            .groupBy { it.categoryId }
            .map {
                val category = if (it.key == null) {
                    null
                } else {
                    categories.first { it2 -> it2.id == it.key }
                }
                val count = it.value.count()
                val amount = it.value.fold(0.0) { acc, movementAggregate -> acc + movementAggregate.amount }
                CategoryAggregate(
                    title = category?.title ?: "Sin clasificar",
                    amount = amount,
                    count = count,
                    group = category?.group ?: "Sin clasificar",
                )
            }
    }
}