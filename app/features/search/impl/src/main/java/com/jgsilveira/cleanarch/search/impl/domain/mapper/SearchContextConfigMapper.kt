package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel
import com.jgsilveira.cleanarch.search.model.SearchContext

internal interface SearchContextConfigMapper {
    fun fromOrigin(origin: SearchOrigin): SearchContextConfigModel
    fun fromContext(context: SearchContext): SearchContextConfigModel
}

internal object SearchContextConfigMapperImpl : SearchContextConfigMapper {
    private val originContextMap = mapOf(
        SearchOrigin.HOME to SearchContext.HOME,
        SearchOrigin.BENEFITS to SearchContext.BENEFITS
    )

    private val contextConfigMap = mapOf(
        SearchContext.HOME to HomeSearchContextConfigFactory,
        SearchContext.BENEFITS to BenefitsSearchContextConfigFactory
    )

    override fun fromOrigin(origin: SearchOrigin): SearchContextConfigModel {
        val context = originContextMap[origin]
            ?: error("Invalid search origin: $origin")
        return fromContext(context)
    }

    override fun fromContext(context: SearchContext): SearchContextConfigModel {
        val contextConfigFactory = contextConfigMap[context]
            ?: error("Search context config not found: $context")
        val contextConfig = contextConfigFactory.createContextConfig()
        return contextConfig.takeIf { it.isEnabled } ?: SearchContextConfigModel(
            originBusinessContext = contextConfig.originBusinessContext
        )
    }
}