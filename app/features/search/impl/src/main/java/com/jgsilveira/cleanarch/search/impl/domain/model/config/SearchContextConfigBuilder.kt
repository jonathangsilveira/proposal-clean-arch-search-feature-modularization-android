package com.jgsilveira.cleanarch.search.impl.domain.model.config

internal interface SearchContextConfigBuilder {
    fun build(): SearchContextConfig
}

internal class FeatureAContextConfigBuilder: SearchContextConfigBuilder {
    override fun build(): SearchContextConfig {
        return DefaultContextConfig(
            originConfig = DefaultOriginConfig(
                name = "FEATURE_A_CONTEXT",
                screen = "FEATURE_A"
            )
        )
    }
}

internal class FeatureBContextConfigBuilder: SearchContextConfigBuilder {
    override fun build(): SearchContextConfig {
        return DefaultContextConfig(
            serializationVersion = SerializationVersion.V2,
            group = SearchGroup.PEOPLE,
            queryLimit = 3,
            originConfig = DefaultOriginConfig(
                name = "FEATURE_B_CONTEXT",
                screen = "FEATURE_B"
            )
        )
    }
}
