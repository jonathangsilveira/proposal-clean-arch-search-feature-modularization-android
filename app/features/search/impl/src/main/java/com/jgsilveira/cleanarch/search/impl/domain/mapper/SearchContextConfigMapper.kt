package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import com.jgsilveira.cleanarch.search.impl.domain.model.config.BenefitsSearchContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.DefaultSearchContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.HomeSearchContextConfig
import com.jgsilveira.cleanarch.search.model.SearchContext
import com.jgsilveira.cleanarch.search.model.SearchContextConfig

internal interface SearchContextConfigMapper {
    fun fromOrigin(origin: SearchOrigin): SearchContextConfig
    fun fromContext(context: SearchContext): SearchContextConfig
}

internal object SearchContextConfigMapperImpl : SearchContextConfigMapper {
    private val originContextMap = mapOf(
        SearchOrigin.HOME to SearchContext.HOME,
        SearchOrigin.BENEFITS to SearchContext.BENEFITS
    )

    private val contextConfigMap = mapOf(
        SearchContext.HOME to HomeSearchContextConfig(),
        SearchContext.BENEFITS to BenefitsSearchContextConfig()
    )

    override fun fromOrigin(origin: SearchOrigin): SearchContextConfig {
        val context = originContextMap[origin]
            ?: error("Invalid search origin: $origin")
        return fromContext(context)
    }

    override fun fromContext(context: SearchContext): SearchContextConfig {
        val contextConfig = contextConfigMap[context]
            ?: error("Search context config not found: $context")
        return contextConfig.takeIf { it.isEnabled } ?: DefaultSearchContextConfig(
            originBusinessContext = contextConfig.originBusinessContext
        )
    }
}