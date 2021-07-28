package com.ereyesalvarez.domain.economy.input

interface CategoryCreateUseCase {
    fun execute(categoryTitle: String, categoryGroup: String)
}