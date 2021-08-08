package com.ereyesalvarez.domain.movement.services

import com.ereyesalvarez.domain.common.exception.EconomyNotFoundException
import com.ereyesalvarez.domain.movement.input.MovementSetCategoryByIdListUseCase
import com.ereyesalvarez.domain.category.output.CategoryPersistentPort
import com.ereyesalvarez.domain.movement.output.MovementPersistentPort

class MovementSetCategoryByIdListService(
    private val movementPersistentPort: MovementPersistentPort,
    private val categoryPersistentPort: CategoryPersistentPort
) : MovementSetCategoryByIdListUseCase {
    override fun execute(ids: List<String>, categoryId: String) {
        val category =
            categoryPersistentPort.findById(categoryId) ?: throw EconomyNotFoundException("Category not found")
        movementPersistentPort.updateCategoryIdListByMovementId(ids, category.id)
    }
}