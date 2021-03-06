package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.category.input.*
import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.input.*
import com.ereyesalvarez.util.parseLocalDate
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/category")
class CategoryResource(
    private val categoryFindAllUseCase: CategoryFindAllUseCase,
    private val categoryCreateUseCase: CategoryCreateUseCase,
    private val categoryGetAggregateInfoUseCase: CategoryGetAggregateInfoUseCase,
    private val categoryGetAggregateMonthUseCase: CategoryGetAggregateMonthUseCase,
    private val categoryGetAggregateMonthUseByCategoryUseCase: CategoryGetAggregateMonthUseByCategoryUseCase,
    private val categoryGetGroupedByGroupMonthlyUseCase: CategoryGetGroupedByGroupMonthlyUseCase
) {
    @GET
    @Path("")
    @RolesAllowed("USER")
    fun listCategories() = categoryFindAllUseCase.execute()

    @POST
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun createCategory(input: CategoryCreateDTO) = categoryCreateUseCase.execute(input.categoryTitle, input.categoryGroup)

    @GET
    @Path("aggregate")
    @RolesAllowed("USER")
    fun categoryGetAggregateInfo(
        @QueryParam("startDate") startDate: String? = null,
        @QueryParam("endDate") endDate: String? = null,
        @QueryParam("income") income: Boolean = true,
    ) = categoryGetAggregateInfoUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))


    @GET
    @Path("aggregate/monthly")
    @RolesAllowed("USER")
    fun categoryGetAggregateMonthly(
        @QueryParam("startDate") startDate: String? = null,
        @QueryParam("endDate") endDate: String? = null,
        @QueryParam("income") income: Boolean = true,
    ) = categoryGetAggregateMonthUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))

    @GET
    @Path("aggregate/monthly/by-category")
    @RolesAllowed("USER")
    fun categoryGetAggregateMonthUseByCategory(
        @QueryParam("startDate") startDate: String? = null,
        @QueryParam("endDate") endDate: String? = null,
        @QueryParam("income") income: Boolean = true,
    ) = categoryGetGroupedByGroupMonthlyUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))


}

