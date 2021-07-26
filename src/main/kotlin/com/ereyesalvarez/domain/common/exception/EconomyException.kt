package com.ereyesalvarez.domain.common.exception

open class EconomyException(override val message: String?) : RuntimeException(message)

class EconomyNotFoundException(override val message: String?) : RuntimeException(message)

