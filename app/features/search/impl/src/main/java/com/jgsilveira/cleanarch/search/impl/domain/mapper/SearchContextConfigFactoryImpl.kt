package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import com.jgsilveira.cleanarch.search.impl.domain.model.config.DefaultContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.FeatureAContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.FeatureBContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.model.SearchContext

internal object SearchContextConfigFactoryImpl : SearchContextConfigFactory {
    private val originContextMap = mapOf(
        SearchOrigin.FEATURE_A to SearchContext.FEATURE_A,
        SearchOrigin.FEATURE_B to SearchContext.FEATURE_B
    )

    private val contextConfigMap = mapOf(
        SearchContext.FEATURE_A to FeatureAContextConfig(),
        SearchContext.FEATURE_B to FeatureBContextConfig()
    )

    override fun fromOrigin(origin: SearchOrigin): SearchContextConfig {
        val context = originContextMap[origin]
            ?: error("Invalid search origin: $origin")
        return fromContext(context)
    }

    override fun fromContext(context: SearchContext): SearchContextConfig {
        val contextConfig = contextConfigMap[context]
            ?: error("Search context config not found: $context")
        if (contextConfig.isEnabled.not()) {
            return DefaultContextConfig(contextConfig.originConfig)
        }
        return contextConfig
    }
}