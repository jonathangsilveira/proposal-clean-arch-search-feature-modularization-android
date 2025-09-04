package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.impl.domain.model.config.OriginBusinessContext
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel

internal object FeatureASearchContextConfigFactory: SearchContextConfigFactory {
    override fun createContextConfig(): SearchContextConfigModel {
        return SearchContextConfigModel(
            originBusinessContext = OriginBusinessContext(
                contextName = "A_FEATURE",
                previousScreen = "A_FEATURE_SCREEN"
            )
        )
    }
}