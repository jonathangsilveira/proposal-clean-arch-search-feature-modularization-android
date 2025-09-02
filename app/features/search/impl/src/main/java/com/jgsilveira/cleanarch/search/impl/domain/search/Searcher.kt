package com.jgsilveira.cleanarch.search.impl.domain.search

import com.jgsilveira.cleanarch.search.impl.domain.repository.SearchRepository
import com.jgsilveira.cleanarch.search.model.SearchResultSection
import com.jgsilveira.cleanarch.search.searchable.Searchable

internal class Searcher(
    private val paramsFactory: SearchParamsFactory,
    private val repository: SearchRepository
): Searchable {

    override suspend fun search(query: String): List<SearchResultSection> {
        val params = paramsFactory.createSearchParams(query)
        return repository.search(params)
    }
}