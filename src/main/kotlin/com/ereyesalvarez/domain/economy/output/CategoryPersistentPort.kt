package com.ereyesalvarez.domain.economy.output

import com.ereyesalvarez.domain.economy.Category

interface CategoryPersistentPort {
    fun findAll(): List<Category>
    fun findById(id: String): Category?
    fun findByTitle(title: String): Category?
    fun findByTitleAndGroup(title: String, group:String): Category?
    fun persist(category: Category)

}