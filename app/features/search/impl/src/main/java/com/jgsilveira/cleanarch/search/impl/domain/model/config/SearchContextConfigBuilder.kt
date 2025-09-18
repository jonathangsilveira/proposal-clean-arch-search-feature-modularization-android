package com.jgsilveira.cleanarch.search.impl.domain.model.config

internal interface SearchContextConfigBuilder {
    fun build(): SearchContextConfig
}

internal class FeatureAContextConfigBuilder: SearchContextConfigBuilder {
    override fun build(): SearchContextConfig {
        return ContextConfigModel(
            originConfig = OriginConfigModel(
                name = "FEATURE_A_CONTEXT",
                screen = "FEATURE_A"
            )
        )
    }
}

internal class FeatureBContextConfigBuilder: SearchContextConfigBuilder {
    override fun build(): SearchContextConfig {
        return ContextConfigModel(
            version = SearchVersion.V2,
            type = "PEOPLE",
            queryLimit = 3,
            originConfig = OriginConfigModel(
                name = "FEATURE_B_CONTEXT",
                screen = "FEATURE_B"
            )
        )
    }
}
