package com.jgsilveira.cleanarch.search.impl.domain.model.config

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ArgsContextConfig(
    override val serializationVersion: SerializationVersion,
    override val group: SearchGroup,
    override val queryLimit: Int,
    override val isEnabled: Boolean,
    override val usesDeviceLocation: Boolean,
    override val originConfig: OriginConfig
): Parcelable, SearchContextConfig {

    constructor(contextConfig: SearchContextConfig) : this(
        serializationVersion = contextConfig.serializationVersion,
        group = contextConfig.group,
        queryLimit = contextConfig.queryLimit,
        usesDeviceLocation = contextConfig.usesDeviceLocation,
        isEnabled = contextConfig.isEnabled,
        originConfig = with(contextConfig.originConfig) {
            OriginConfig(
                uuid = uuid,
                name = name,
                screen = screen
            )
        }
    )

    @Parcelize
    data class OriginConfig(
        override val uuid: String,
        override val name: String,
        override val screen: String
    ): Parcelable, SearchOriginConfig
}
