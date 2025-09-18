package com.jgsilveira.cleanarch.search.impl.domain.model.config

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ArgsContextConfig(
    override val version: SearchVersion,
    override val type: String,
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
                    version = version,
                    type = type,
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
