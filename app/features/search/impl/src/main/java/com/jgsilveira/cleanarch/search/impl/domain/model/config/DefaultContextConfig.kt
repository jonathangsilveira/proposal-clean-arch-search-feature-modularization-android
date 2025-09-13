package com.jgsilveira.cleanarch.search.impl.domain.model.config

import java.util.UUID

internal data class DefaultContextConfig(
    override val originConfig: SearchOriginConfig,
    override val serializationVersion: SerializationVersion = SerializationVersion.V3,
    override val group: SearchGroup = SearchGroup.GENERAL,
    override val queryLimit: Int = 2,
    override val isEnabled: Boolean = true,
    override val usesDeviceLocation: Boolean = true
) : SearchContextConfig

internal data class DefaultOriginConfig(
    override val name: String,
    override val screen: String,
    override val uuid: String = UUID.randomUUID().toString()
): SearchOriginConfig