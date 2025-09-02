package com.jgsilveira.cleanarch.search.searchable

import com.jgsilveira.cleanarch.search.model.SearchResultSection

interface Searchable {
    suspend fun search(query: String): List<SearchResultSection>
}