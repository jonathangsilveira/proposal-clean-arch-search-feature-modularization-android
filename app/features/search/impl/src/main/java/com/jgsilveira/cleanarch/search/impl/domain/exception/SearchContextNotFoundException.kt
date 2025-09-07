package com.jgsilveira.cleanarch.search.impl.domain.exception

import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin

private const val MESSAGE = "Invalid search origin: %s"

class SearchContextNotFoundException internal constructor(
    origin: SearchOrigin
): Exception(MESSAGE.format(origin))