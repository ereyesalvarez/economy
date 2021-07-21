package com.ereyesalvarez.domain.economy.output

import com.ereyesalvarez.domain.economy.Category

interface CategoryPersistentPort{
    fun findAll(): List<Category>
    fun findById(id: String): Category?
}