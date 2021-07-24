package com.ereyesalvarez.domain.economy.output

import com.ereyesalvarez.application.infrastructure.mongo.toEntity
import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.Movement

interface CategoryPersistentPort{
    fun findAll(): List<Category>
    fun findById(id: String): Category?
    fun findByTitle(title: String): Category?
    fun persist(category: Category)

}