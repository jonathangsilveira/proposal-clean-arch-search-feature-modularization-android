package com.jgsilveira.cleanarch.search.model

import java.util.UUID

data class OriginBusinessContext(
    val contextName: String,
    val previousScreen: String,
    val uuid: String = UUID.randomUUID().toString()
)
