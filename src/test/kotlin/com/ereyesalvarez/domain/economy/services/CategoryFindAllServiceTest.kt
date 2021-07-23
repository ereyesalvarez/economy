package com.ereyesalvarez.domain.economy.services

import com.ereyesalvarez.domain.economy.output.CategoryPersistentPort
import com.ereyesalvarez.test.util.generateMockCategoryList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito

internal class CategoryFindAllServiceTest {

    private lateinit var categoryFindAllService: CategoryFindAllService;

    @Test
    fun execute() {
        val categoryPersistentPort = Mockito.mock(CategoryPersistentPort::class.java)
        val categoryList = generateMockCategoryList(3)
        Mockito.`when`(categoryPersistentPort.findAll()).thenReturn(categoryList)
        categoryFindAllService = CategoryFindAllService(categoryPersistentPort)
        val result =  categoryFindAllService.execute()
        assertEquals(result, categoryList)
        Mockito.verify(categoryPersistentPort).findAll()
    }
}