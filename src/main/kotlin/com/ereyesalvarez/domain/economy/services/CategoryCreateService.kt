package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.common.exception.EconomyException
import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.input.CategoryCreateUseCase
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort

class CategoryCreateService(private val categoryPersistentPort: CategoryPersistentPort) : CategoryCreateUseCase {
    override fun execute(categoryTitle: String, categoryGroup: String?) {
        if (categoryPersistentPort.findByTitle(title = categoryTitle) !== null) {
            throw EconomyException("Category with this title is already exist")
        }
        val category = Category(title = categoryTitle, group = categoryGroup)
        categoryPersistentPort.persist(category)
    }
}