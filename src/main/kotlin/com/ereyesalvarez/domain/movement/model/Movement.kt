package com.ereyesalvarez.domain.movement.model

import com.ereyesalvarez.util.generateDomainId
import java.time.LocalDate

data class Movement(
    val id: String = generateDomainId(),
    var title: String,
    var date: LocalDate,
    val transactions: MutableList<Transaction> = mutableListOf(),
    var categoryId: String? = null,
    val comment: String? = null
){
    fun transactionSum(): Double {
        return transactions.fold(0.0) { acc, transaction ->
            if(transaction.income){
                acc - transaction.amount
            } else {
                acc + transaction.amount
            }
        }
    }
}