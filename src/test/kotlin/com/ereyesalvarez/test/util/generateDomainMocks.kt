package com.ereyesalvarez.test.util

import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.Transaction
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import java.time.LocalDate
import kotlin.random.Random

const val STRING_LENGTH = 10
const val RANDOM_SEED = 131
const val ALPHANUMERIC_REGEX = "[a-zA-Z0-9]+"

fun generateMockCategory() = Category(title = randomAlphanumeric(STRING_LENGTH))

fun generateMockCategory(id: String) = Category(id = id, title = randomAlphanumeric(STRING_LENGTH))

fun generateMockCategoryList(n: Int): List<Category> {
    return (1..n).map { generateMockCategory() }
}


fun generateMockMovementList(n: Int, categoryId: String? = null): List<Movement> {
    return (1..n).map { generateMockMovement(categoryId) }
}

fun generateMockMovement(categoryId: String?): Movement {
    val transaction = generateMockTransaction()
    val transactions = mutableListOf(transaction)
    return Movement(
        title = transaction.concept,
        date = transaction.date,
        transactions = transactions,
        categoryId = categoryId
    )
}

fun generateMockTransaction(): Transaction {
    return generateMockTransaction(LocalDate.now().minusDays(3))
}

fun generateMockTransaction(date: LocalDate): Transaction {
    return Transaction(
        date = date,
        valueDate = date,
        concept = randomAlphanumeric(STRING_LENGTH),
        amount = Random(RANDOM_SEED).nextDouble(),
        balance = Random(RANDOM_SEED).nextDouble(),
        imported = Random(RANDOM_SEED).nextBoolean()
    )
}