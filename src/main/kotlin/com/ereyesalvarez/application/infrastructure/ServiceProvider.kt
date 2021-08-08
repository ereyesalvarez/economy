package com.ereyesalvarez.application.infrastructure

import com.ereyesalvarez.domain.category.input.*
import com.ereyesalvarez.domain.category.service.*
import com.ereyesalvarez.domain.movement.input.*
import com.ereyesalvarez.domain.category.output.CategoryPersistentPort
import com.ereyesalvarez.domain.movement.output.MovementPersistentPort
import com.ereyesalvarez.domain.movement.services.*
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

    @Produces
    @ApplicationScoped
    fun categoryGetAggregateMonthUseCase(): CategoryGetAggregateMonthUseCase =
        CategoryGetAggregateMonthService(movementFindAllUseCase(), categoryFindAllUseCase())

    @Produces
    @ApplicationScoped
    fun categoryGetAggregateMonthUseByCategoryUseCase(): CategoryGetAggregateMonthUseByCategoryUseCase =
        CategoryGetAggregateMonthUseByCategoryService(movementFindAllUseCase(), categoryFindAllUseCase())

    @Produces
    @ApplicationScoped
    fun categoryGetGroupedByGroupMonthlyUseCase(): CategoryGetGroupedByGroupMonthlyUseCase =
        CategoryGetGroupedByGroupMonthlyService(movementFindAllUseCase(), categoryFindAllUseCase())
}