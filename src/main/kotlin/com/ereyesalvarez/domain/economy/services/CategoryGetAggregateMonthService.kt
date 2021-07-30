package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.CategoryExpenseByMonth
import com.ereyesalvarez.domain.economy.MovementAggregate
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.CategoryGetAggregateMonthUseCase
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.economy.mapper.mapToMovementAggregateList

class CategoryGetAggregateMonthService(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryFindAllUseCase: CategoryFindAllUseCase
) :
    CategoryGetAggregateMonthUseCase {
    override fun execute(filter: FilterCommand): List<CategoryExpenseByMonth> {
        val categories = categoryFindAllUseCase.execute()
        return movementFindAllUseCase.execute(filter)
            .groupBy { classify(it) }
            .map { entry ->
                CategoryExpenseByMonth(
                    entry.key.year,
                    entry.key.month,
                    mapToMovementAggregateList(entry.value, categories)
                )
            }
    }

    private fun classify(input: MovementAggregate): MonthClassifyDTO {
        return MonthClassifyDTO(input.date.year, input.date.monthValue)
    }

    data class MonthClassifyDTO(val year: Int, val month: Int)
}
