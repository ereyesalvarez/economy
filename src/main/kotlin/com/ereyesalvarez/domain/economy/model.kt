package com.ereyesalvarez.domain.economy

import com.ereyesalvarez.util.generateDomainId
import java.time.LocalDate

data class Transaction(
    val id: String = generateDomainId(),
    val date: LocalDate,
    val concept: String,
    val amount: Double,
    val valueDate: LocalDate? = date,
    val balance: Double?,
    val income: Boolean = false,
    val imported: Boolean = false
)

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

data class Category(
    val id: String = generateDomainId(),
    val title: String,
    val group: String? = null
)