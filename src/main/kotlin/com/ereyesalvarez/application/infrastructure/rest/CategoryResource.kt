package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.input.CategoryByConceptGetUseCase
import com.ereyesalvarez.domain.economy.input.CategoryFindAllUseCase
import com.ereyesalvarez.domain.economy.input.MovementSetCategoryByIdUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/category")
class CategoryResource(
    private val categoryFindAllUseCase: CategoryFindAllUseCase
){
    @GET
    @Path("")
    @RolesAllowed("USER")
    fun listCategories() = categoryFindAllUseCase.execute()


}