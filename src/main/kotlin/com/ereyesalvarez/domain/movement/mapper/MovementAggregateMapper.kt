package com.ereyesalvarez.domain.movement.mapper

import com.ereyesalvarez.domain.category.CategoryAggregate
import com.ereyesalvarez.domain.category.model.Category
import com.ereyesalvarez.domain.movement.MovementAggregate

fun mapToMovementAggregateList(input: List<MovementAggregate>, categories: List<Category>):
        List<CategoryAggregate> {
    return input.groupBy { it.categoryId }
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
                group = category?.group ?: "Sin clasificar",
                amount = amount,
                count = count,
            )
        }
}
