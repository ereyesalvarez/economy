package com.ereyesalvarez.application.infrastructure.rest

import com.ereyesalvarez.domain.common.exception.EconomyException
import com.ereyesalvarez.domain.common.exception.EconomyNotFoundException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class EconomyExceptionHandler : ExceptionMapper<EconomyException> {
    override fun toResponse(exception: EconomyException): Response {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErrorDto(exception.message)).build()
    }
}

@Provider
class EconomyNotFoundExceptionHandler : ExceptionMapper<EconomyNotFoundException> {
    override fun toResponse(exception: EconomyNotFoundException): Response {
        return Response.status(Response.Status.NOT_FOUND).entity(ErrorDto(exception.message)).build()
    }
}