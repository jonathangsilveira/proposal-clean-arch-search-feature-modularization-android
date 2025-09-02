package com.jgsilveira.cleanarch.search.impl.domain.model.config

import android.os.Parcelable
import com.jgsilveira.cleanarch.search.model.SearchGroup
import com.jgsilveira.cleanarch.search.model.SerializationVersion
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class DefaultSearchContextConfig(
    override val originBusinessContext: OriginBusinessContext,
    override val serializationVersion: SerializationVersion = SerializationVersion.V3,
    override val group: SearchGroup = SearchGroup.GENERAL,
    override val queryLimit: Int = 3,
    override val isEnabled: Boolean = true,
    override val usesDeviceLocation: Boolean = true
): SearchContextConfig, Parcelable