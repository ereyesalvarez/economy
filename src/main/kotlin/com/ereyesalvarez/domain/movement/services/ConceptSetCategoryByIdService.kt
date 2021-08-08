package com.ereyesalvarez.domain.movement.services

import com.ereyesalvarez.domain.common.exception.EconomyNotFoundException
import com.ereyesalvarez.domain.movement.input.ConceptSetCategoryByIdUseCase
import com.ereyesalvarez.domain.category.output.CategoryPersistentPort
import com.ereyesalvarez.domain.movement.output.MovementPersistentPort

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