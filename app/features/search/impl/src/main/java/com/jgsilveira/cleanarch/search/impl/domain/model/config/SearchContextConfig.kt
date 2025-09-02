package com.jgsilveira.cleanarch.search.impl.domain.model.config

import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion

internal interface SearchContextConfig {
    val serializationVersion: SerializationVersion
    val group: SearchGroup
    val queryLimit: Int
    val isEnabled: Boolean
    val usesDeviceLocation: Boolean
    val originBusinessContext: OriginBusinessContext
}