package com.jgsilveira.cleanarch.search.impl.presentation

internal sealed interface SearchUIEvent {
    data class SearchBarChange(
        val query: String
    ): SearchUIEvent
}