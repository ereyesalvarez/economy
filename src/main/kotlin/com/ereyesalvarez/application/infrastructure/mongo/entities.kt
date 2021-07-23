package com.ereyesalvarez.application.infrastructure.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoCompanion
import io.quarkus.mongodb.panache.kotlin.PanacheMongoCompanionBase
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntityBase
import java.time.LocalDate

@MongoEntity(collection = "movements")
class MovementEntity : PanacheMongoEntityBase() {
    companion object : PanacheMongoCompanionBase<MovementEntity, String> {
        fun findByTitle(title: String) = find("title", title).list()

        fun updateCategoryIdByIds(ids: List<String>, categoryId: String) =
            update("categoryId", categoryId).where("_id in ?1", ids)

        fun updateCategoryIdById(id: String, categoryId: String) =
            update("categoryId", categoryId).where("_id = ?1", id)

        fun updateCategoryIdByTitle(title: String, categoryId: String) =
            update("categoryId", categoryId).where("title = ?1", title)
    }

    var id: String? = null
    var title: String? = null
    var date: LocalDate? = null
    var categoryId: String? = null
    var transactions: MutableList<TransactionEntity>? = null
    var comment: String? = null
}

data class TransactionEntity(
    var id: String? = null,
    var amount: Double? = null,
    var date: LocalDate? = null,
    var concept: String? = null,
    var valueDate: LocalDate? = null,
    var balance: Double? = null,
    var imported: Boolean? = null
)

@MongoEntity(collection = "categories")
class CategoryEntity : PanacheMongoEntityBase() {
    companion object : PanacheMongoCompanionBase<CategoryEntity, String> {
        fun findByTitle(title: String) = CategoryEntity.find("title", title).firstResult()
    }
    var id: String? = null
    var title: String? = null
}

@MongoEntity(collection = "users")
class UserEntity : PanacheMongoEntityBase() {
    companion object : PanacheMongoCompanionBase<UserEntity, String> {
        fun findByUsername(username: String) = UserEntity.find("username", username).firstResult()
    }

    var id: String? = null
    var username: String? = null
    var password: String? = null
    var roles: MutableSet<String>? = null
}