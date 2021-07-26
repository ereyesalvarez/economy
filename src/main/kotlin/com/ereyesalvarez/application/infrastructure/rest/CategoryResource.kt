package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.input.CategoryCreateUseCase
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.CategoryGetAggregateInfoUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
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
    fun createCategory(input: CategoryCreateDTO) = categoryCreateUseCase.execute(input.categoryTitle)

    @GET
    @Path("aggregate")
    @RolesAllowed("USER")
    fun categoryGetAggregateInfo() = categoryGetAggregateInfoUseCase.execute()


}

