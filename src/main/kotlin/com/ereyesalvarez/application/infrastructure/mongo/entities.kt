package com.ereyesalvarez.application.infrastructure.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoCompanion
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity
import org.bson.types.ObjectId
import java.time.LocalDate

@MongoEntity(collection = "movements")
class MovementEntity : PanacheMongoEntity() {
    companion object : PanacheMongoCompanion<MovementEntity> {
        fun findByTitle(title: String) = find("title", title).firstResult()
        fun updateCategoryIdByIds(ids: List<ObjectId>, categoryId: String) =  update("categoryId", categoryId).where("_id in ?1", ids)
        fun updateCategoryIdById(id: String, categoryId: String) =  update("categoryId", categoryId).where("_id = ?1", ObjectId(id))
    }

    var title: String? = null
    var date: LocalDate? = null
    var categoryId: String? = null
    var transactions: MutableList<TransactionEntity>? = null
}


data class TransactionEntity(
        var id: String? = null,
        var amount: Double? = null,
        var date: LocalDate? = null,
        var concept: String? = null,
)

@MongoEntity(collection = "categories")
class CategoryEntity : PanacheMongoEntity() {
    companion object : PanacheMongoCompanion<CategoryEntity> {
        fun findByTitle(title: String) = CategoryEntity.find("title", title).firstResult()
    }

    var title: String? = null
}

@MongoEntity(collection = "users")
class UserEntity: PanacheMongoEntity() {
    companion object: PanacheMongoCompanion<UserEntity> {
        fun findByUsername(username: String) = UserEntity.find("username", username).firstResult()
    }
    var username: String? = null
    var password: String? = null
    var roles: MutableSet<String>? = null
}