package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.CategoryAggregate
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.CategoryGetAggregateInfoUseCase
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase

class CategoryGetAggregateInfoService(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryFindAllUseCase: CategoryFindAllUseCase
) :
    CategoryGetAggregateInfoUseCase {
    override fun execute(): List<CategoryAggregate> {
        val categories = categoryFindAllUseCase.execute()
        return movementFindAllUseCase.execute()
            .groupBy { it.categoryId }
            .map {
                val key = if (it.key == null) {
                    "Sin clasificar"
                } else {
                    categories.first { it2 -> it2.id == it.key }.title
                }
                val count = it.value.count()
                val amount = it.value.fold(0.0) { acc, movementAggregate -> acc + movementAggregate.amount }
                CategoryAggregate(
                    title = key,
                    amount = amount,
                    count = count
                )
            }
    }
}