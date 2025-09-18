package com.jgsilveira.cleanarch.search.impl.domain.model.params

internal data class SearchParams(
    val version: String,
    val analyticParams: AnalyticParams,
    val query: String,
    val queryLimit: Int,
    val type: String,
    val deviceLocation: DeviceLocation?
)