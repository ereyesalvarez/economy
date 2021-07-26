package com.ereyesalvarez.application.infrastructure.mongo

import com.ereyesalvarez.domain.economy.Category

fun Category.toEntity(): CategoryEntity {
    val entity = CategoryEntity()
    entity.id = id
    entity.title = title
    entity.group = group
    return entity
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id ?: throw MongoMapperException("error at mapping"),
        title = title ?: throw MongoMapperException("error at mapping"),
        group = group
    )

}