package com.ereyesalvarez.domain.movement.input

interface TransactionImportListAndGenerateMovementUseCase {
    fun execute(input: List<TransactionImportCommand>)
}
