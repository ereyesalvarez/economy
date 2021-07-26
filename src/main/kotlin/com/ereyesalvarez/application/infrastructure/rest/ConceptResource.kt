package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.ConceptAggregate
import com.ereyesalvarez.domain.economy.input.ConceptFindAllDistinctUseCase
import com.ereyesalvarez.domain.economy.input.ConceptSetCategoryByIdUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/movement/concept")
class ConceptResource(
    private val conceptFindAllDistinctUseCase: ConceptFindAllDistinctUseCase,
    private val conceptSetCategoryByIdUseCase: ConceptSetCategoryByIdUseCase
) {
    @GET
    @Path("")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<ConceptAggregate> {
        return conceptFindAllDistinctUseCase.execute()
    }

    @PUT
    @Path("category")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun setCategory(input: CategoryConceptDTO) = conceptSetCategoryByIdUseCase.execute(input.concept, input.categoryId)
}