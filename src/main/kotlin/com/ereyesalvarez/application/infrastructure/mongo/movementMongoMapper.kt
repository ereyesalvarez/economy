package com.ereyesalvarez.poverty.application.infrastructure.mongo

import com.ereyesalvarez.application.infrastructure.mongo.MovementEntity
import com.ereyesalvarez.application.infrastructure.mongo.TransactionEntity
import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.Transaction
import com.ereyesalvarez.poverty.domain.movement.Movement
import com.ereyesalvarez.poverty.domain.movement.Transaction
import org.bson.types.ObjectId

fun Movement.toEntity(): MovementEntity {
    val entity = MovementEntity()
    if(id !== null){
        entity.id = ObjectId(id)
    }
    entity.title = title
    entity.date = date
    entity.categoryId = categoryId
    entity.transactions = transactions.map {
        TransactionEntity(
            it.id,
            it.amount,
            it.date,
            it.concept
        )
    }.toMutableList()
    return entity
}

fun MovementEntity.toDomain(): Movement {
    val domainTransactions = transactions?.map {
        Transaction(
            it.id ?: throw RuntimeException("error at mapping"),
            it.date ?: throw RuntimeException("error at mapping"),
            it.amount ?: throw RuntimeException("error at mapping"),
            it.concept ?: throw RuntimeException("error at mapping"),
        )
    }?.toMutableList() ?: mutableListOf()
    return Movement(
        id?.toString() ?: throw RuntimeException("error at mapping"),
        title ?: throw RuntimeException("error at mapping"),
        date ?: throw RuntimeException("error at mapping"),
        categoryId,
        domainTransactions
    )

}