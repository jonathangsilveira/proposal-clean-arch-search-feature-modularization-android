package com.jgsilveira.cleanarch.search.impl.domain.model.config

import java.util.UUID

internal class FeatureAContextConfig: SearchContextConfig {
    override val serializationVersion: SerializationVersion = SerializationVersion.V3
    override val group: SearchGroup = SearchGroup.GENERAL
    override val queryLimit: Int = 3
    override val isEnabled: Boolean = true
    override val usesDeviceLocation: Boolean = true
    override val originConfig: SearchOriginConfig = OriginConfig(
        name = "FEATURE_A_CONTEXT",
        screen = "FEATURE_A"
    )

    data class OriginConfig(
        override val name: String,
        override val screen: String,
        override val uuid: String = UUID.randomUUID().toString()
    ): SearchOriginConfig
}
