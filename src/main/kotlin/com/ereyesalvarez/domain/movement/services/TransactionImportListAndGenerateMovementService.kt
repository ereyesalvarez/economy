package com.ereyesalvarez.domain.movement.services

import com.ereyesalvarez.domain.category.input.CategoryByConceptGetUseCase
import com.ereyesalvarez.domain.category.model.Category
import com.ereyesalvarez.domain.movement.input.TransactionImportCommand
import com.ereyesalvarez.domain.movement.input.TransactionImportListAndGenerateMovementUseCase
import com.ereyesalvarez.domain.movement.model.Movement
import com.ereyesalvarez.domain.movement.model.Transaction
import com.ereyesalvarez.domain.movement.output.MovementPersistentPort
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.abs

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
            date = command.date, amount = abs(command.amount), valueDate = command.valueDate,
            concept = command.concept, balance = command.balance, imported = true, income = command.amount >= 0
        )
        // Crear el movimiento y asociar la transacción, si hay categoría asignarla
        val movement = Movement(title = command.concept, date = command.date, categoryId = category?.id)
        movement.transactions.add(transaction)
        return movement
    }
}