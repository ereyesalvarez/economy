package com.ereyesalvarez.domain.category.model

import com.ereyesalvarez.util.generateDomainId

data class Category(
    val id: String = generateDomainId(),
    val title: String,
    val group: String
)