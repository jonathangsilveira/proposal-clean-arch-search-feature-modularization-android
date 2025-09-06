package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.model.SearchContext

internal interface SearchContextConfigFactory {
    fun fromOrigin(origin: SearchOrigin): SearchContextConfig
    fun fromContext(context: SearchContext): SearchContextConfig
}