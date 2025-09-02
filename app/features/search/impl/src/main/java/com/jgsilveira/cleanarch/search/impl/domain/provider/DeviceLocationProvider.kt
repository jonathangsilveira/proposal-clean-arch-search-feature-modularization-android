package com.jgsilveira.cleanarch.search.impl.domain.provider

import com.jgsilveira.cleanarch.search.impl.domain.model.params.DeviceLocation

internal interface DeviceLocationProvider {
    fun getDeviceLocation(): DeviceLocation?
}