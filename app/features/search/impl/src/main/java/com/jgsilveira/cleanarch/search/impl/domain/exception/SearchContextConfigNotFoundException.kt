package com.jgsilveira.cleanarch.search.impl.domain.exception

import com.jgsilveira.cleanarch.search.model.SearchContext

private const val MESSAGE = "Search context config not found: %s"

class SearchContextConfigNotFoundException internal constructor(
    context: SearchContext
): Exception(MESSAGE.format(context))