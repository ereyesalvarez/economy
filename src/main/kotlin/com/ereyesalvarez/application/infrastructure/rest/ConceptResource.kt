package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.economy.ConceptAggregate
import com.ereyesalvarez.domain.economy.input.ConceptFindAllDistinctUseCase
import com.ereyesalvarez.domain.economy.input.ConceptSetCategoryByIdUseCase
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.util.parseLocalDate
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
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
    fun findAll(
        @QueryParam("startDate") startDate: String? = null,
        @QueryParam("endDate") endDate: String? = null,
        @QueryParam("income") income: Boolean = false,
    ): List<ConceptAggregate> {
        return conceptFindAllDistinctUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))
    }

    @PUT
    @Path("category")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun setCategory(input: CategoryConceptDTO) = conceptSetCategoryByIdUseCase.execute(input.concept, input.categoryId)
}