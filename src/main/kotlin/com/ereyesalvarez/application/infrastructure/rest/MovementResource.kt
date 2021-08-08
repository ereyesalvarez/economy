package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.MovementAggregate
import com.ereyesalvarez.domain.movement.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.movement.input.MovementSetCategoryByIdListUseCase
import com.ereyesalvarez.domain.movement.input.MovementSetCategoryByIdUseCase
import com.ereyesalvarez.util.parseLocalDate
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/movement")
class MovementResource(
    private val movementFindAllUseCase: MovementFindAllUseCase,
    private val categoryByIdUseCase: MovementSetCategoryByIdUseCase,
    private val movementSetCategoryByIdListUseCase: MovementSetCategoryByIdListUseCase
) {

    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun list(
        @QueryParam("startDate") startDate: String? = null,
        @QueryParam("endDate") endDate: String? = null,
        @QueryParam("income") income: Boolean = true,
    ): List<MovementAggregate> {
        return movementFindAllUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))
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