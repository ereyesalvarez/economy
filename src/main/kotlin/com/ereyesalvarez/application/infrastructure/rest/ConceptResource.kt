package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.common.input.command.FilterCommand
import com.ereyesalvarez.domain.movement.ConceptAggregate
import com.ereyesalvarez.domain.movement.input.ConceptFindAllDistinctUseCase
import com.ereyesalvarez.domain.movement.input.ConceptSetCategoryByIdUseCase
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
        @QueryParam("income") income: Boolean = true,
    ): List<ConceptAggregate> {
        return conceptFindAllDistinctUseCase.execute(FilterCommand(parseLocalDate(startDate), parseLocalDate(endDate), income))
    }

    @PUT
    @Path("category")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun setCategory(input: CategoryConceptDTO) = conceptSetCategoryByIdUseCase.execute(input.concept, input.categoryId)
}