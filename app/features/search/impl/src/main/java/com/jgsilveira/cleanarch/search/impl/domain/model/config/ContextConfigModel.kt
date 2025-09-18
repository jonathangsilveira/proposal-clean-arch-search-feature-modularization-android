package com.jgsilveira.cleanarch.search.impl.domain.model.config

import java.util.UUID

internal data class ContextConfigModel(
    override val originConfig: SearchOriginConfig,
    override val version: SearchVersion = SearchVersion.V3,
    override val type: String = "ALL",
    override val queryLimit: Int = 2,
    override val isEnabled: Boolean = true,
    override val usesDeviceLocation: Boolean = true
) : SearchContextConfig

internal data class OriginConfigModel(
    override val name: String,
    override val screen: String,
    override val uuid: String = UUID.randomUUID().toString()
): SearchOriginConfig