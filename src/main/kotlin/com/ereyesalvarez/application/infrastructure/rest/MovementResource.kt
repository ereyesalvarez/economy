package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.MovementAggregate
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdListUseCase
import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/movement")
class MovementResource(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryByIdUseCase: MovementSetCategoryByIdUseCase,
    private val movementSetCategoryByIdListUseCase: MovementSetCategoryByIdListUseCase
) {

    @GET
    @Path("")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun list(): List<MovementAggregate> {
        return movementFindAllUseCase.execute()
    }

    @PUT
    @Path("category")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun setCategory(input: CategoryByMovementIdDTO) = categoryByIdUseCase.execute(input.movementId, input.categoryId)

    @PUT
    @Path("category/batch")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun setCategoryBatch(input: CategoryByMovementIdListDTO) =
        movementSetCategoryByIdListUseCase.execute(input.movementIdList, input.categoryId)
}