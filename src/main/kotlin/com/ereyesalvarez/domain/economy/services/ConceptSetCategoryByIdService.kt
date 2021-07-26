package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.common.exception.EconomyNotFoundException
import com.ereyesalvarez.domain.economy.input.ConceptSetCategoryByIdUseCase
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

class ConceptSetCategoryByIdService(
    private val movementPersistentPort: MovementPersistentPort,
    private val categoryPersistentPort: CategoryPersistentPort,
) : ConceptSetCategoryByIdUseCase {

    override fun execute(title: String, categoryId: String) {
        val category =
            categoryPersistentPort.findById(categoryId) ?: throw EconomyNotFoundException("Category not found")
        movementPersistentPort.updateCategoryIdByTitle(title, category.id)
    }
}