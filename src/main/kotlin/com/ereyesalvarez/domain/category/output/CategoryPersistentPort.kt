package com.ereyesalvarez.domain.category.output

import com.ereyesalvarez.domain.category.model.Category

interface CategoryPersistentPort {
    fun findAll(): List<Category>
    fun findById(id: String): Category?
    fun findByTitle(title: String): Category?
    fun findByTitleAndGroup(title: String, group:String): Category?
    fun persist(category: Category)

}