package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.Movement
import com.ereyesalvarez.domain.economy.input.FilterCommand
import com.ereyesalvarez.domain.economy.output.MovementPersistentPort
import com.ereyesalvarez.test.util.generateMockMovement
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.time.LocalDate
import java.util.stream.Stream

class MovementFindAllServiceTest {

    companion object {
        @JvmStatic
        fun movementFindAllServiceTestFilterData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(null, null, 4),
                Arguments.of(LocalDate.of(2021,12,12), null, 2),
                Arguments.of(null, LocalDate.of(2021,12,12), 3),
                Arguments.of(LocalDate.of(2021,12,12), LocalDate.of(2021,12,13), 2)
            )
        }
    }
    @ParameterizedTest
    @MethodSource
    fun movementFindAllServiceTestFilterData(startDate: LocalDate?, endDate: LocalDate?, size: Int) {
        val movementPersistentPort: MovementPersistentPort = Mockito.mock(MovementPersistentPort::class.java)
        Mockito.`when`(movementPersistentPort.findAll()).thenReturn(
            listOf(
                generateMockMovement(LocalDate.of(2021,12,13)),
                generateMockMovement(LocalDate.of(2021,12,12)),
                generateMockMovement(LocalDate.of(2021,12,11)),
                generateMockMovement(LocalDate.of(2021,12,10)),
            )
        )

        val movementFindAllService = MovementFindAllService(movementPersistentPort)
        val result = movementFindAllService.execute(FilterCommand(startDate, endDate))
        Assertions.assertEquals(result.size, size)
    }
}