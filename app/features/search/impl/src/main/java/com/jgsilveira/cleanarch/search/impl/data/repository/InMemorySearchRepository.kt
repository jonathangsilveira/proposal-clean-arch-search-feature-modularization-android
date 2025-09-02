package com.jgsilveira.cleanarch.search.impl.data.repository

import com.jgsilveira.cleanarch.search.impl.domain.model.params.SearchParams
import com.jgsilveira.cleanarch.search.impl.domain.repository.SearchRepository
import com.jgsilveira.cleanarch.search.model.SearchResultSection
import kotlinx.coroutines.delay

internal class InMemorySearchRepository: SearchRepository {
    override suspend fun search(params: SearchParams): List<SearchResultSection> {
        delay(300)
        return listOf()
    }
}