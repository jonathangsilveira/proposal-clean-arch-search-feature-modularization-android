package com.jgsilveira.cleanarch.search.impl.domain.mapper

import com.jgsilveira.cleanarch.search.impl.domain.model.config.OriginBusinessContext
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel

internal object HomeSearchContextConfigFactory: SearchContextConfigFactory {
    override fun createContextConfig(): SearchContextConfigModel {
        return SearchContextConfigModel(
            originBusinessContext = OriginBusinessContext(
                contextName = "HOME_FEATURE",
                previousScreen = "HOME_SCREEN"
            )
        )
    }
}