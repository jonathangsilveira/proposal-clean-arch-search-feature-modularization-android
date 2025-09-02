package com.jgsilveira.cleanarch.search.impl.domain.model.config

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
internal data class OriginBusinessContext(
    val contextName: String,
    val previousScreen: String,
    val uuid: String = UUID.randomUUID().toString()
): Parcelable
