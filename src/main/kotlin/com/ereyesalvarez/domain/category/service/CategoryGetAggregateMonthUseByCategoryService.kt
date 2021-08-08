package com.ereyesalvarez.domain.category.service

import com.ereyesalvarez.domain.category.CategoryMonthAggregate
import com.ereyesalvarez.domain.category.CategoryMonthAggregatePart
import com.ereyesalvarez.domain.category.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.category.input.CategoryGetAggregateMonthUseByCategoryUseCase
import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.MovementAggregate
import com.ereyesalvarez.domain.movement.input.MovementFindAllUseCase

class CategoryGetAggregateMonthUseByCategoryService(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryFindAllUseCase: CategoryFindAllUseCase
) : CategoryGetAggregateMonthUseByCategoryUseCase {
    override fun execute(filter: FilterCommand): List<CategoryMonthAggregate> {
        val categories = categoryFindAllUseCase.execute()
        return movementFindAllUseCase.execute(filter)
            .groupBy { it.categoryId }
            .map { it ->
                val category = if (it.key == null) {
                    null
                } else {
                    categories.first { it2 -> it2.id == it.key }
                }
                CategoryMonthAggregate(
                    title = category?.title ?: "Sin clasificar",
                    group = category?.group ?: "Sin clasificar",
                    dates = it.value.groupBy { classify(it) }
                        .map { entry ->
                            val count = entry.value.count()
                            val amount =
                                entry.value.fold(0.0) { acc, movementAggregate -> acc + movementAggregate.amount }
                            CategoryMonthAggregatePart(
                                year = entry.key.year,
                                month = entry.key.month,
                                count = count,
                                amount = amount
                            )
                        }

                )
            }
    }

    private fun classify(input: MovementAggregate): CategoryGetAggregateMonthService.MonthClassifyDTO {
        return CategoryGetAggregateMonthService.MonthClassifyDTO(input.date.year, input.date.monthValue)
    }

}