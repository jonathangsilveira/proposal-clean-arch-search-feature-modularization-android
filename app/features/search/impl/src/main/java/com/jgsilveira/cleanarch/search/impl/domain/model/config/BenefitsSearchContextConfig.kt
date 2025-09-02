package com.jgsilveira.cleanarch.search.impl.domain.model.config

import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion

internal class BenefitsSearchContextConfig: SearchContextConfig {
    override val serializationVersion = SerializationVersion.V2
    override val group = SearchGroup.BENEFITS_P2P
    override val queryLimit: Int = 3
    override val isEnabled = true
    override val usesDeviceLocation: Boolean = false
    override val originBusinessContext = OriginBusinessContext(
        contextName = "BENEFITS_FEATURE",
        previousScreen = "BENEFITS_SHARING"
    )
}