package com.ereyesalvarez.domain.category.service

import com.ereyesalvarez.domain.category.CategoryGroupMonthAggregate
import com.ereyesalvarez.domain.category.CategoryMonthAggregatePart
import com.ereyesalvarez.domain.category.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.category.input.CategoryGetGroupedByGroupMonthlyUseCase
import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.MovementAggregate
import com.ereyesalvarez.domain.movement.input.MovementFindAllUseCase
import java.time.LocalDate

class CategoryGetGroupedByGroupMonthlyService(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryFindAllUseCase: CategoryFindAllUseCase
) : CategoryGetGroupedByGroupMonthlyUseCase {
    override fun execute(filter: FilterCommand): List<CategoryGroupMonthAggregate> {
        val categories = categoryFindAllUseCase.execute()
        return movementFindAllUseCase.execute(filter)
            .groupBy { it.categoryId }
            .flatMap { entry: Map.Entry<String?, List<MovementAggregate>> ->
                val category = if (entry.key == null) {
                    null
                } else {
                    categories.first { it2 -> it2.id == entry.key }
                }
                entry.value.map { value ->
                    MovementWithGroup(
                        date = value.date,
                        title = value.title,
                        amount = value.amount,
                        categoryTitle = category?.title ?: "Sin clasificar",
                        categoryGroup = category?.group ?: "Sin clasificar",
                    )
                }
            }.groupBy { it.categoryGroup }
            .map {
                CategoryGroupMonthAggregate(
                    group = it.key,
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
}

private fun classify(input: MovementWithGroup): CategoryGetAggregateMonthService.MonthClassifyDTO {
    return CategoryGetAggregateMonthService.MonthClassifyDTO(input.date.year, input.date.monthValue)
}

data class MovementWithGroup(
    val date: LocalDate,
    val title: String,
    val amount: Double,
    val categoryTitle: String,
    val categoryGroup: String,
)

