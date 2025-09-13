package com.jgsilveira.cleanarch.search.impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.impl.presentation.mapper.SearchErrorMapper
import com.jgsilveira.cleanarch.search.impl.presentation.mapper.SearchResultSectionMapper
import com.jgsilveira.cleanarch.search.impl.presentation.model.SearchResultUIData
import com.jgsilveira.cleanarch.search.model.SearchResultSection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val SEARCH_DEBOUNCE_MS = 500L

@OptIn(FlowPreview::class)
internal class SearchViewModel(
    private val contextConfig: SearchContextConfig,
    private val useCaseProvider: SearchUseCaseProvider
): ViewModel() {
    private val searchBarText = MutableStateFlow("")

    private val mutableUiState = MutableStateFlow<SearchUIState>(SearchUIState.Empty)

    private val uiEffectChannel = Channel<SearchUIEffect>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    val uiEffects: Flow<SearchUIEffect>
        get() = uiEffectChannel.receiveAsFlow()

    val uiState: StateFlow<SearchUIState>
        get() = mutableUiState

    init {
        viewModelScope.launch {
            searchBarText.debounce(SEARCH_DEBOUNCE_MS)
                .distinctUntilChanged()
                .map { query -> query.trim() }
                .flowOn(context = Dispatchers.Default)
                .onEach {
                    mutableUiState.update { SearchUIState.Searching }
                }
                .map { query ->
                    if (query.length >= contextConfig.queryLimit) {
                        useCaseProvider.search(query)
                    } else {
                        Result.success(emptyList())
                    }
                }
                .map { result ->
                    handleSearchResult(result)
                }
                .stateIn(viewModelScope)
        }
    }

    fun dispatchEvent(uiEvent: SearchUIEvent) {
        when (uiEvent) {
            is SearchUIEvent.SearchBarChange -> {
                onSearchBarChanged(uiEvent.query)
            }

            SearchUIEvent.NavBackClick -> {
                uiEffectChannel.trySend(SearchUIEffect.NavigateBack)
            }
        }
    }

    private fun onSearchBarChanged(query: String) {
        viewModelScope.launch {
            searchBarText.emit(query)
        }
    }

    private fun handleSearchResult(
        result: Result<List<SearchResultSection>>
    ) {
        result.mapCatching { sections ->
            sections.mapNotNull {
                SearchResultSectionMapper.map(it)
            }
        }.onSuccess { results ->
            handleSuccess(results)
        }.onFailure { error ->
            handleFailure(error)
        }
    }

    private fun handleSuccess(results: List<SearchResultUIData>) {
        mutableUiState.update {
            if (results.isEmpty()) {
                SearchUIState.Empty
            } else {
                SearchUIState.Results(results)
            }
        }
    }

    private fun handleFailure(error: Throwable) {
        val errorMessage = error.message ?: "No root cause"
        Log.d("SEARCH", "Error on handle search results: $errorMessage", error)
        mutableUiState.update {
            val feedback = SearchErrorMapper.map(error)
            SearchUIState.Error(listOf(feedback))
        }
    }
}