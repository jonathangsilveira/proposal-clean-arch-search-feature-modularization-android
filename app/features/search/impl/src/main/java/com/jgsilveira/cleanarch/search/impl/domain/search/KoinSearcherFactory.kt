package com.jgsilveira.cleanarch.search.impl.domain.search

import com.jgsilveira.cleanarch.search.impl.domain.mapper.SearchContextConfigMapper
import com.jgsilveira.cleanarch.search.model.SearchContext
import com.jgsilveira.cleanarch.search.searchable.Searchable
import com.jgsilveira.cleanarch.search.factory.SearcherFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

internal class KoinSearcherFactory(
    private val contextConfigMapper: SearchContextConfigMapper
): SearcherFactory, KoinComponent {

    override fun createSearcher(context: SearchContext): Searchable {
        val contextConfig = contextConfigMapper.fromContext(context)
        return get { parametersOf(contextConfig) }
    }
}