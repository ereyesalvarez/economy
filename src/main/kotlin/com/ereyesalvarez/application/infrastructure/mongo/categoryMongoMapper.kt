package com.ereyesalvarez.poverty.application.infrastructure.mongo

import com.ereyesalvarez.application.infrastructure.mongo.CategoryEntity
import com.ereyesalvarez.poverty.domain.movement.Category

fun Category.toEntity(): CategoryEntity {
    val entity = CategoryEntity()
    if(id !== null){
        entity.id = ObjectId(id)
    }
    entity.title = title
    return entity
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id?.toString() ?: throw RuntimeException("error at mapping"),
        title ?: throw RuntimeException("error at mapping"),
    )

}