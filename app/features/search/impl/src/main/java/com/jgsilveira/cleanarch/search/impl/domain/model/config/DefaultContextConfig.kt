package com.jgsilveira.cleanarch.search.impl.domain.model.config

internal data class DefaultContextConfig(
    override val originConfig: SearchOriginConfig,
    override val serializationVersion: SerializationVersion = SerializationVersion.V3,
    override val group: SearchGroup = SearchGroup.GENERAL,
    override val queryLimit: Int = 2,
    override val isEnabled: Boolean = true,
    override val usesDeviceLocation: Boolean = true
) : SearchContextConfig
