package com.ereyesalvarez.domain.economy.input

interface TransactionImportListAndGenerateMovementUseCase {
    fun execute(input: List<TransactionImportCommand>)
}
