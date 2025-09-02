package com.jgsilveira.cleanarch.search.impl.domain.model.config

import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion

internal class HomeSearchContextConfig: SearchContextConfig {
    override val serializationVersion = SerializationVersion.V3
    override val group = SearchGroup.GENERAL
    override val queryLimit: Int = 3
    override val isEnabled = true
    override val usesDeviceLocation: Boolean = true
    override val originBusinessContext = OriginBusinessContext(
        contextName = "HOME_APP",
        previousScreen = "HOME"
    )
}