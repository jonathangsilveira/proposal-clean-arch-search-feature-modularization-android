package com.jgsilveira.cleanarch.search.model

interface SearchContextConfig {
    val serializationVersion: SerializationVersion
    val group: SearchGroup
    val queryLimit: Int
    val isEnabled: Boolean
    val usesDeviceLocation: Boolean
    val originBusinessContext: OriginBusinessContext
}