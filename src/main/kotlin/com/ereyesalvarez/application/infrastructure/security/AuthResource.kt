package com.ereyesalvarez.application.infrastructure.security

import javax.annotation.security.PermitAll
import javax.enterprise.context.RequestScoped
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/auth")
@RequestScoped
class AuthResource(
    private val securityService: SecurityService
) {
    @POST
    @Path("/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    fun login(input: LoginDto): BearerOutDto {
        return BearerOutDto(securityService.validateUserLogin(input.username, input.password))
    }
}
