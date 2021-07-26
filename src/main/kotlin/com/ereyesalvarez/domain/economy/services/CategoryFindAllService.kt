package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort

class CategoryFindAllService(private val categoryPersistentPort: CategoryPersistentPort) : CategoryFindAllUseCase {
    override fun execute(): List<Category> {
        return categoryPersistentPort.findAll()
    }
}