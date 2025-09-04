package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.impl.domain.model.config.OriginBusinessContext
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel
import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion

internal object FeatureBSearchContextConfigFactory: SearchContextConfigFactory {
    override fun createContextConfig(): SearchContextConfigModel {
        return SearchContextConfigModel(
            serializationVersion = SerializationVersion.V2,
            group = SearchGroup.BENEFITS_P2P,
            usesDeviceLocation = false,
            originBusinessContext = OriginBusinessContext(
                contextName = "B_FEATURE",
                previousScreen = "B_FEATURE_SCREEN"
            )
        )
    }
}