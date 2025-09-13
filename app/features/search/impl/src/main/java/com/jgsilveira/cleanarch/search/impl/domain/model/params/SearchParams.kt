package com.jgsilveira.cleanarch.search.impl.domain.model.params

internal data class SearchParams(
    val serializationVersion: String,
    val analyticParams: AnalyticParams,
    val query: String,
    val queryLimit: Int,
    val group: String,
    val deviceLocation: DeviceLocation?
)