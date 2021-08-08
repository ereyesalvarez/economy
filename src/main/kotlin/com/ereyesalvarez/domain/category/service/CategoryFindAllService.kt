package com.ereyesalvarez.domain.category.service

import com.ereyesalvarez.domain.category.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.category.model.Category
import com.ereyesalvarez.domain.category.output.CategoryPersistentPort

class CategoryFindAllService(private val categoryPersistentPort: CategoryPersistentPort) : CategoryFindAllUseCase {
    override fun execute(): List<Category> {
        return categoryPersistentPort.findAll()
    }
}