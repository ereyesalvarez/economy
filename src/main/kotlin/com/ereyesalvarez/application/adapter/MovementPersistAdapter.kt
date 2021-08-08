package com.ereyesalvarez.application.adapter

import com.ereyesalvarez.application.infrastructure.mongo.MovementEntity
import com.ereyesalvarez.application.infrastructure.mongo.toDomain
import com.ereyesalvarez.application.infrastructure.mongo.toEntity
import com.ereyesalvarez.domain.movement.model.Movement
import com.ereyesalvarez.domain.movement.output.MovementPersistentPort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovementPersistAdapter: MovementPersistentPort {
    override fun findAll(): List<Movement> {
        return MovementEntity.listAll().map { it.toDomain() }
    }

    override fun findByTitle(title: String): List<Movement> {
        return MovementEntity.findByTitle(title).map { it.toDomain() }
    }

    override fun updateCategoryIdByMovementId(id: String, categoryId: String) {
        MovementEntity.updateCategoryIdById(id, categoryId)
    }

    override fun updateCategoryIdByTitle(title: String, categoryId: String) {
        MovementEntity.updateCategoryIdByTitle(title, categoryId)
    }

    override fun updateCategoryIdListByMovementId(ids: List<String>, categoryId: String) {
        MovementEntity.updateCategoryIdByIds(ids, categoryId)
    }

    override fun persist(movement: Movement) {
        movement.toEntity().persist()
    }

    override fun persistList(movement: Iterable<Movement>) {
        MovementEntity.persist(movement.map { it.toEntity() })
    }
}