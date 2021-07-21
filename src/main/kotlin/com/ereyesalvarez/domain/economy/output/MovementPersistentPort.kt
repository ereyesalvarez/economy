
package com.ereyesalvarez.domain.economy.output

import com.ereyesalvarez.domain.economy.Movement

interface MovementPersistentPort{
    fun findAll(): List<Movement>
    fun findById(id: String): Movement?
    fun persist(movement: Movement)
    fun findByConcept(concept: String): List<Movement>
    fun updateCategoryIdByMovementId(id: String, categoryId: String)
}