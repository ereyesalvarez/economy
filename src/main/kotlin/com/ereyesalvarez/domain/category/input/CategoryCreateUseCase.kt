package com.ereyesalvarez.domain.category.input

interface CategoryCreateUseCase {
    fun execute(categoryTitle: String, categoryGroup: String)
}