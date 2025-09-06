package com.jgsilveira.cleanarch.search.impl.data.search

import com.jgsilveira.cleanarch.search.impl.domain.model.params.AnalyticHeaders
import com.jgsilveira.cleanarch.search.impl.domain.model.params.SearchParams
import com.jgsilveira.cleanarch.search.impl.domain.provider.DeviceLocationProvider
import com.jgsilveira.cleanarch.search.impl.domain.provider.TrackerSessionIdProvider
import com.jgsilveira.cleanarch.search.impl.domain.search.SearchParamsFactory
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig

internal class SearchParamsFactoryImpl(
    private val contextConfig: SearchContextConfig,
    private val deviceLocationProvider: DeviceLocationProvider,
    private val trackerSessionIdProvider: TrackerSessionIdProvider
): SearchParamsFactory {

    override fun createSearchParams(query: String): SearchParams {
        return with(contextConfig) {
            SearchParams(
                serializationVersion = serializationVersion.name,
                analyticHeaders = with(contextConfig.originConfig) {
                    AnalyticHeaders(
                        sessionId = trackerSessionIdProvider.getTrackerSessionId(),
                        id = uuid,
                        context = name,
                        screen = screen
                    )
                },
                group = group.name,
                queryLimit = queryLimit,
                query = query,
                deviceLocation = if (usesDeviceLocation) {
                    deviceLocationProvider.getDeviceLocation()
                } else null
            )
        }
    }
}