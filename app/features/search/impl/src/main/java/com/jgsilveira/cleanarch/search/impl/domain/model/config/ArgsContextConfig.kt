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

    @Parcelize
    data class OriginConfig(
        override val uuid: String,
        override val name: String,
        override val screen: String
    ): Parcelable, SearchOriginConfig

    companion object {

        fun fromContextConfig(contextConfig: SearchContextConfig): ArgsContextConfig {
            return with(contextConfig) {
                ArgsContextConfig(
                    serializationVersion = serializationVersion,
                    group = group,
                    queryLimit = queryLimit,
                    usesDeviceLocation = usesDeviceLocation,
                    isEnabled = isEnabled,
                    originConfig = with(originConfig) {
                        OriginConfig(
                            uuid = uuid,
                            name = name,
                            screen = screen
                        )
                    }
                )
            }
        }
    }
}
