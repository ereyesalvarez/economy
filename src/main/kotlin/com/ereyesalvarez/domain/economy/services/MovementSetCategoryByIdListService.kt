package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.common.exception.EconomyNotFoundException
import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdListUseCase
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort

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