package com.jgsilveira.cleanarch.search.impl.data.provider

import com.jgsilveira.cleanarch.search.impl.domain.provider.TrackerSessionIdProvider
import java.util.UUID

internal class InMemoryTrackerSessionIdProvider: TrackerSessionIdProvider {
    private val uuid = UUID.randomUUID().toString()

    override fun getTrackerSessionId() = uuid
}