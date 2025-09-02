package com.jgsilveira.cleanarch.search.impl.usecase

import com.jgsilveira.cleanarch.search.model.SearchResultSection
import com.jgsilveira.cleanarch.search.searchable.Searchable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class SearchUseCase(
    private val searcher: Searchable,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {

    suspend operator fun invoke(query: String): Result<List<SearchResultSection>> {
        return runCatching {
            withContext(coroutineContext) {
                searcher.search(query)
            }
        }
    }
}