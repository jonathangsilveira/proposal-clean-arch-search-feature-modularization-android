package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel

internal interface SearchContextConfigFactory {
    fun createContextConfig(): SearchContextConfigModel
}