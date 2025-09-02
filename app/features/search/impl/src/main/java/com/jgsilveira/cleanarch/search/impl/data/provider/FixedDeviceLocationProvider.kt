package com.jgsilveira.cleanarch.search.impl.data.provider

import com.jgsilveira.cleanarch.search.impl.domain.model.params.DeviceLocation
import com.jgsilveira.cleanarch.search.impl.domain.provider.DeviceLocationProvider

internal object FixedDeviceLocationProvider: DeviceLocationProvider {
    override fun getDeviceLocation(): DeviceLocation {
        return DeviceLocation(long = 27.9881, lat = 86.9250)
    }
}