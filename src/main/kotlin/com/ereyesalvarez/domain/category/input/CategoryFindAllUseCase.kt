package com.ereyesalvarez.domain.category.input

import com.ereyesalvarez.domain.category.model.Category

interface CategoryFindAllUseCase {
    fun execute(): List<Category>
}