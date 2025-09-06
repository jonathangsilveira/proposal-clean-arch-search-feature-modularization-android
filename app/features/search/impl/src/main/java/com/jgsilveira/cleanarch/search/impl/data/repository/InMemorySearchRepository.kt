package com.jgsilveira.cleanarch.search.impl.data.repository

import com.jgsilveira.cleanarch.search.impl.domain.model.params.SearchParams
import com.jgsilveira.cleanarch.search.impl.domain.repository.SearchRepository
import com.jgsilveira.cleanarch.search.model.FeedbackInfo
import com.jgsilveira.cleanarch.search.model.FeedbackSearchResultSection
import com.jgsilveira.cleanarch.search.model.ListItem
import com.jgsilveira.cleanarch.search.model.ListSearchResultSection
import com.jgsilveira.cleanarch.search.model.SearchResultSection
import kotlinx.coroutines.delay
import kotlin.random.Random

internal class InMemorySearchRepository: SearchRepository {

    override suspend fun search(params: SearchParams): List<SearchResultSection> {
        delay(300)
        if (shouldFail())
            throw Exception("Error on search: ${params.query}")
        return if (isNoResults()) {
            noResultsFeedback()
        } else {
            resultsFor(params.query)
        }
    }

    private fun shouldFail(): Boolean {
        return Random.nextBoolean()
    }

    private fun isNoResults(): Boolean {
        return Random.nextBoolean()
    }

    private fun noResultsFeedback(): List<SearchResultSection> {
        return listOf(
            FeedbackSearchResultSection(
                title = null,
                info = FeedbackInfo(
                    title = "Ops! We could not found what you want",
                    message = "Try to type a better query"
                )
            )
        )
    }

    private fun resultsFor(query: String): List<SearchResultSection> {
        return listOf(
            ListSearchResultSection(
                title = "Results for '$query'",
                items = List(5) { index ->
                    ListItem(
                        title = "Title #$index",
                        body = "Body #$index",
                        footer = if (Random.nextBoolean()) {
                            "Footer #$index"
                        } else null
                    )
                }
            )
        )
    }
}