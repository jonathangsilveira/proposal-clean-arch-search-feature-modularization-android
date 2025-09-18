package com.jgsilveira.cleanarch.search.impl.domain.model.config

internal interface SearchContextConfig {
    val version: SearchVersion
    val type: String
    val queryLimit: Int
    val isEnabled: Boolean
    val usesDeviceLocation: Boolean
    val originConfig: SearchOriginConfig
}

internal interface SearchOriginConfig {
    val uuid: String
    val name: String
    val screen: String
}