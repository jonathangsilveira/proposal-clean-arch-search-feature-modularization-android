package com.jgsilveira.cleanarch.search.impl.presentation

import com.jgsilveira.cleanarch.search.model.SearchResultSection

internal sealed interface SearchUIState {
    data object Empty: SearchUIState
    data object Searching: SearchUIState
    data class Results(
        val sections: List<SearchResultSection>
    ): SearchUIState
    data class Error(
        val message: String
    ): SearchUIState
}