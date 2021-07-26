package com.ereyesalvarez.application.infrastructure

import com.ereyesalvarez.domain.economy.input.*
import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort
import com.ereyesalvarez.domain.economy.services.*
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.Produces

class ServiceProvider(
    private val movementPersistentPort: MovementPersistentPort,
    private val categoryPersistentPort: CategoryPersistentPort
) {
    @Produces
    @ApplicationScoped
    fun categoryFindAllUseCase(): CategoryFindAllUseCase = CategoryFindAllService(categoryPersistentPort)

    @Produces
    @ApplicationScoped
    fun conceptFindAllDistinctUseCase(): ConceptFindAllDistinctUseCase =
        ConceptFindAllDistinctService(movementFindAllUseCase())

    @Produces
    @ApplicationScoped
    fun conceptSetCategoryByIdUseCase(): ConceptSetCategoryByIdUseCase =
        ConceptSetCategoryByIdService(movementPersistentPort, categoryPersistentPort)

    @Produces
    @ApplicationScoped
    fun movementFindAllUseCase(): MovementFindAllUseCase = MovementFindAllService(movementPersistentPort)

    @Produces
    @ApplicationScoped
    fun movementSetCategoryByIdUseCase(): MovementSetCategoryByIdUseCase =
        MovementSetCategoryByIdService(movementPersistentPort)

    @Produces
    @ApplicationScoped
    fun categoryByConceptGetUseCase(): CategoryByConceptGetUseCase = CategoryByConceptGetService()

    @Produces
    @ApplicationScoped
    fun transactionImportListAndGenerateMovementUseCase(): TransactionImportListAndGenerateMovementUseCase =
        TransactionImportListAndGenerateMovementService(categoryByConceptGetUseCase(), movementPersistentPort)

    @Produces
    @ApplicationScoped
    fun movementSetCategoryByIdListUseCase(): MovementSetCategoryByIdListUseCase =
        MovementSetCategoryByIdListService(movementPersistentPort, categoryPersistentPort)

    @Produces
    @ApplicationScoped
    fun categoryCreateUseCase(): CategoryCreateUseCase = CategoryCreateService(categoryPersistentPort)

    @Produces
    @ApplicationScoped
    fun categoryGetAggregateInfoUseCase(): CategoryGetAggregateInfoUseCase =
        CategoryGetAggregateInfoService(movementFindAllUseCase(), categoryFindAllUseCase())
}