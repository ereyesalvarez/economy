package com.ereyesalvarez.domain.category.service

import com.ereyesalvarez.domain.common.exception.EconomyException
import com.ereyesalvarez.domain.category.input.CategoryCreateUseCase
import com.ereyesalvarez.domain.category.model.Category
import com.ereyesalvarez.domain.category.output.CategoryPersistentPort

class CategoryCreateService(private val categoryPersistentPort: CategoryPersistentPort) : CategoryCreateUseCase {
    override fun execute(categoryTitle: String, categoryGroup: String) {
        if (categoryPersistentPort.findByTitleAndGroup(title = categoryTitle, group = categoryGroup) !== null) {
            throw EconomyException("Category with this title and category already exist")
        }
        val category = Category(title = categoryTitle, group = categoryGroup)
        categoryPersistentPort.persist(category)
    }
}