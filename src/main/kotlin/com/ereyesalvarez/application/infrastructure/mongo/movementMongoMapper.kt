package com.ereyesalvarez.application.infrastructure.mongo

import com.ereyesalvarez.domain.movement.model.Movement
import com.ereyesalvarez.domain.movement.model.Transaction

fun Movement.toEntity(): MovementEntity {
    val entity = MovementEntity()
    entity.id = id
    entity.title = title
    entity.date = date
    entity.categoryId = categoryId
    entity.transactions = transactions.map {
        TransactionEntity(
            id = it.id,
            date = it.date,
            amount = it.amount,
            concept = it.concept,
            valueDate = it.valueDate,
            balance = it.balance,
            imported = it.imported,
            income = it.income
        )
    }.toMutableList()
    return entity
}

fun MovementEntity.toDomain(): Movement {
    val domainTransactions = transactions?.map {
        Transaction(
            id = it.id ?: throw MongoMapperException("error at mapping"),
            date = it.date ?: throw MongoMapperException("error at mapping"),
            valueDate = it.valueDate ?: throw MongoMapperException("error at mapping"),
            concept = it.concept ?: throw MongoMapperException("error at mapping"),
            amount = it.amount ?: throw MongoMapperException("error at mapping"),
            balance = it.balance,
            imported = it.imported ?: throw MongoMapperException("error at mapping"),
            income = it.income ?: throw MongoMapperException("error at mapping"),
        )
    }?.toMutableList() ?: mutableListOf()
    return Movement(
        id = id ?: throw MongoMapperException("error at mapping"),
        title = title ?: throw MongoMapperException("error at mapping"),
        date = date ?: throw MongoMapperException("error at mapping"),
        categoryId = categoryId,
        comment = comment,
        transactions = domainTransactions
    )

}