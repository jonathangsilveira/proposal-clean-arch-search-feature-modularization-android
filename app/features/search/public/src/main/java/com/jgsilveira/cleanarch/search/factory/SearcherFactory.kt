package com.jgsilveira.cleanarch.search.factory

import com.jgsilveira.cleanarch.search.model.SearchContext
import com.jgsilveira.cleanarch.search.searchable.Searchable

interface SearcherFactory {
    fun createSearcher(context: SearchContext): Searchable
}