package com.ereyesalvarez.domain.movement.output

import com.ereyesalvarez.domain.movement.model.Movement

interface MovementPersistentPort {
    fun findAll(): List<Movement>
    fun findByTitle(title: String): List<Movement>
    fun updateCategoryIdByMovementId(id: String, categoryId: String)
    fun updateCategoryIdListByMovementId(ids: List<String>, categoryId: String)
    fun updateCategoryIdByTitle(title: String, categoryId: String)
    fun persist(movement: Movement)
    fun persistList(movement: Iterable<Movement>)
}