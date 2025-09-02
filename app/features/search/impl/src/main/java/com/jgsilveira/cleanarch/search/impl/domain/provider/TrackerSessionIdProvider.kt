package com.jgsilveira.cleanarch.search.impl.domain.provider

internal interface TrackerSessionIdProvider {
    fun getTrackerSessionId(): String
}