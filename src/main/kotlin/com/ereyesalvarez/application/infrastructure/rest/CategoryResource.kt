package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.input.CategoryCreateUseCase
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.CategoryGetAggregateInfoUseCase
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.util.parseLocalDate
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/category")
class CategoryResource(
    private val categoryFindAllUseCase: CategoryFindAllUseCase,
    private val categoryCreateUseCase: CategoryCreateUseCase,
    private val categoryGetAggregateInfoUseCase: CategoryGetAggregateInfoUseCase
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
        @QueryParam("income") income: Boolean = false,
    ) = categoryGetAggregateInfoUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))


}

