package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.common.exception.EconomyException
import com.ereyesalvarez.domain.economy.input.ConceptSetCategoryByIdUseCase
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

class ConceptSetCategoryByIdService(
    private val movementPersistentPort: MovementPersistentPort,
    private val categoryPersistentPort: CategoryPersistentPort,
    ): ConceptSetCategoryByIdUseCase {
    override fun execute(concept: String, categoryId: String) {
        val category = categoryPersistentPort.findById(categoryId) ?: throw EconomyException("Category not found")
        movementPersistentPort.findByConcept(concept)
            .forEach {
                movementPersistentPort.updateCategoryIdByMovementId(it.id, categoryId)
                it.categoryId = category.id
            }
    }
}