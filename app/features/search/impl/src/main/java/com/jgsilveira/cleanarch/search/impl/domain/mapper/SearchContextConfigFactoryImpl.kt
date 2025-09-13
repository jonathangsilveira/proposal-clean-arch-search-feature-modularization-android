package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import com.jgsilveira.cleanarch.search.impl.domain.exception.SearchContextConfigNotFoundException
import com.jgsilveira.cleanarch.search.impl.domain.exception.SearchContextNotFoundException
import com.jgsilveira.cleanarch.search.impl.domain.model.config.DefaultContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.FeatureAContextConfigBuilder
import com.jgsilveira.cleanarch.search.impl.domain.model.config.FeatureBContextConfigBuilder
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.model.SearchContext

internal object SearchContextConfigFactoryImpl : SearchContextConfigFactory {
    private val originContexts = mapOf(
        SearchOrigin.FEATURE_A to SearchContext.FEATURE_A,
        SearchOrigin.FEATURE_B to SearchContext.FEATURE_B
    )

    private val contextConfigBuilders = mapOf(
        SearchContext.FEATURE_A to FeatureAContextConfigBuilder(),
        SearchContext.FEATURE_B to FeatureBContextConfigBuilder()
    )

    override fun fromOrigin(origin: SearchOrigin): SearchContextConfig {
        val context = originContexts[origin]
            ?: throw SearchContextNotFoundException(origin)
        return fromContext(context)
    }

    override fun fromContext(context: SearchContext): SearchContextConfig {
        val contextConfigBuilder = contextConfigBuilders[context]
            ?: throw SearchContextConfigNotFoundException(context)
        val contextConfig = contextConfigBuilder.build()
        if (contextConfig.isEnabled.not()) {
            return DefaultContextConfig(contextConfig.originConfig)
        }
        return contextConfig
    }
}