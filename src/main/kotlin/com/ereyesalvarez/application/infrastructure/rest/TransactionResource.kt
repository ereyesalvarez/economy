package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.movement.input.TransactionImportCommand
import com.ereyesalvarez.domain.movement.input.TransactionImportListAndGenerateMovementUseCase
import javax.annotation.security.RolesAllowed
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/transaction")
class TransactionResource(
    private val transactionImportUseCase: TransactionImportListAndGenerateMovementUseCase
) {

    @POST
    @Path("import")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    fun addInBatch(input: List<TransactionImportCommand>) = transactionImportUseCase.execute(input)
}