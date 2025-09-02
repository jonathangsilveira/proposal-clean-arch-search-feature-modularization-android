package com.jgsilveira.cleanarch.search.impl.domain.repository

import com.jgsilveira.cleanarch.search.impl.domain.model.params.SearchParams
import com.jgsilveira.cleanarch.search.model.SearchResultSection

internal interface SearchRepository {
    suspend fun search(params: SearchParams): List<SearchResultSection>
}