package com.ereyesalvarez.application.adapter

import com.ereyesalvarez.application.infrastructure.mongo.CategoryEntity
import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CategoryPersistAdapter : CategoryPersistentPort {
    override fun findAll(): List<Category> {
        CategoryEntity.listAll()
    }

    override fun findById(id: String): Category? {
        CategoryEntity.findById()
    }
}