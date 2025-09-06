package com.jgsilveira.cleanarch.search.impl.presentation

import com.jgsilveira.cleanarch.search.impl.presentation.model.SearchResultUIData

internal sealed interface SearchUIState {
    data object Empty: SearchUIState
    data object Searching: SearchUIState
    data class Results(
        val sections: List<SearchResultUIData>
    ): SearchUIState
    data class Error(
        val sections: List<SearchResultUIData>
    ): SearchUIState
}