package com.jgsilveira.cleanarch.search.impl.domain.model.config

import java.util.UUID

internal class FeatureBContextConfig: SearchContextConfig {
    override val serializationVersion: SerializationVersion = SerializationVersion.V2
    override val group: SearchGroup = SearchGroup.PEOPLE
    override val queryLimit: Int = 2
    override val isEnabled: Boolean = true
    override val usesDeviceLocation: Boolean = true
    override val originConfig: SearchOriginConfig = OriginConfig(
        name = "FEATURE_B_CONTEXT",
        screen = "FEATURE_B"
    )

    data class OriginConfig(
        override val name: String,
        override val screen: String,
        override val uuid: String = UUID.randomUUID().toString()
    ): SearchOriginConfig
}
