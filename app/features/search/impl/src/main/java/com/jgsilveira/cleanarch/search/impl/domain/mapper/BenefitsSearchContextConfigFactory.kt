package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.impl.domain.model.config.OriginBusinessContext
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel
import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion

internal object BenefitsSearchContextConfigFactory: SearchContextConfigFactory {
    override fun createContextConfig(): SearchContextConfigModel {
        return SearchContextConfigModel(
            serializationVersion = SerializationVersion.V2,
            group = SearchGroup.BENEFITS_P2P,
            usesDeviceLocation = false,
            originBusinessContext = OriginBusinessContext(
                contextName = "BENEFITS_FEATURE",
                previousScreen = "BENEFITS_SHARING_SCREEN"
            )
        )
    }
}