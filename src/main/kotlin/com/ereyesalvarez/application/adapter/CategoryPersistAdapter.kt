package com.ereyesalvarez.application.adapter

import com.ereyesalvarez.application.infrastructure.mongo.CategoryEntity
import com.ereyesalvarez.application.infrastructure.mongo.toDomain
import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CategoryPersistAdapter : CategoryPersistentPort {
    override fun findAll(): List<Category> {
        return CategoryEntity.listAll().map { it.toDomain() }
    }

    override fun findById(id: String): Category? {
        return CategoryEntity.findById(id)?.toDomain();
    }
}