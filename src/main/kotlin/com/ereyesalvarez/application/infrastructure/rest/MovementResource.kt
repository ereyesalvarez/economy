package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.MovementAggregate
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.domain.economy.input.MovementFindAllUseCase
import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdListUseCase
import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdUseCase
import com.ereyesalvarez.util.parseLocalDate
import java.time.LocalDate
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
        @QueryParam("income") income: Boolean = false,
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