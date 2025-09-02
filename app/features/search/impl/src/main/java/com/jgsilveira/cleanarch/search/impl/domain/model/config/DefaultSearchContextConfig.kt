package com.jgsilveira.cleanarch.search.impl.domain.model.config

import com.jgsilveira.cleanarch.search.model.OriginBusinessContext
import com.jgsilveira.cleanarch.search.model.SearchContextConfig
import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion

data class DefaultSearchContextConfig(
    override val originBusinessContext: OriginBusinessContext
): SearchContextConfig {

    override val serializationVersion = SerializationVersion.V3
    override val group: SearchGroup = SearchGroup.GENERAL
    override val queryLimit: Int = 3
    override val isEnabled: Boolean = true
    override val usesDeviceLocation: Boolean = true
}
