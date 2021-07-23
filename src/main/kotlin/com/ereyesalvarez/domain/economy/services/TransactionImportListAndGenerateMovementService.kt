package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.Category
import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.Transaction
import com.ereyesalvarez.domain.economy.input.CategoryByConceptGetUseCase
import com.ereyesalvarez.domain.economy.input.TransactionImportCommand
import com.ereyesalvarez.domain.economy.input.TransactionImportListAndGenerateMovementUseCase
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TransactionImportListAndGenerateMovementService(
    private val categoryByConceptGetUseCase: CategoryByConceptGetUseCase,
    private val movementPersistentPort: MovementPersistentPort
) :
    TransactionImportListAndGenerateMovementUseCase {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun execute(input: List<TransactionImportCommand>) {
        log.debug("START")
        input.forEach { processOneCommand(it) }
        log.debug("END")
    }

    private fun processOneCommand(command: TransactionImportCommand) {
        movementPersistentPort.persist(mapToMovementToInsert(command))
    }

    private fun mapToMovementToInsert(command: TransactionImportCommand): Movement {
        // Primero buscar si el concepto ya existe y si tiene un grupo y categoría asociado
        val category: Category? = categoryByConceptGetUseCase.execute(command.concept)
        // Crear la transacción
        val transaction = Transaction(
            date = command.date, amount = command.amount, valueDate = command.valueDate,
            concept = command.concept, balance = command.balance, imported = true
        )
        // Crear el movimiento y asociar la transacción, si hay categoría asignarla
        val movement = Movement(title = command.concept, date = command.date, categoryId = category?.id)
        movement.transactions.add(transaction)
        return movement;
    }
}