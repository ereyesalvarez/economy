package com.ereyesalvarez.domain.category

import java.time.LocalDate


data class CategoryAggregate(
    val title: String,
    val group: String,
    val amount: Double,
    val count: Int = 0
)

data class CategoryExpenseByMonth(
    val year: Int = 0,
    val month: Int = 0,
    val categories: List<CategoryAggregate>
)

data class CategoryMonthAggregate(
    val title: String,
    val group: String,
    val dates: List<CategoryMonthAggregatePart>

)

data class CategoryMonthAggregatePart(
    val year: Int = 0,
    val month: Int = 0,
    val amount: Double,
    val count: Int = 0
)

data class CategoryGroupMonthAggregate(
    val group: String,
    val dates: List<CategoryMonthAggregatePart>

)
