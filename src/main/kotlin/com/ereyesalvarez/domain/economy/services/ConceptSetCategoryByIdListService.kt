package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.common.exception.EconomyNotFoundException
import com.ereyesalvarez.domain.economy.input.ConceptSetCategoryByIdListUseCase
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

class ConceptSetCategoryByIdListService(
    private val movementPersistentPort: MovementPersistentPort,
    private val categoryPersistentPort: CategoryPersistentPort
) : ConceptSetCategoryByIdListUseCase {
    override fun execute(ids: List<String>, categoryId: String) {
        val category =
            categoryPersistentPort.findById(categoryId) ?: throw EconomyNotFoundException("Category not found")
        movementPersistentPort.updateCategoryIdListByMovementId(ids, category.id)
    }
}