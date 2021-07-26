package com.ereyesalvarez.application.infrastructure.mongo

import com.ereyesalvarez.domain.common.exception.EconomyException

class MongoMapperException(message: String) : EconomyException(message)