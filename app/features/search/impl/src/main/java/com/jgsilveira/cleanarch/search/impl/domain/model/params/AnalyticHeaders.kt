package com.jgsilveira.cleanarch.search.impl.domain.model.params

internal data class AnalyticHeaders(
    val sessionId: String,
    val id: String,
    val context: String,
    val screen: String
)