package com.jgsilveira.cleanarch.search.impl.domain.search

import com.jgsilveira.cleanarch.search.impl.domain.model.params.SearchParams

internal interface SearchParamsFactory {
    fun createSearchParams(query: String): SearchParams
}