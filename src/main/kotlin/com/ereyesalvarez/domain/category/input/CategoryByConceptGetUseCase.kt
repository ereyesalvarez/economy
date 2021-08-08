package com.ereyesalvarez.domain.category.input

import com.ereyesalvarez.domain.category.model.Category


interface CategoryByConceptGetUseCase {
    fun execute(concept: String): Category?
}
