@file:OptIn(ExperimentalMaterial3Api::class)

package com.jgsilveira.cleanarch.search.impl.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jgsilveira.cleanarch.search.impl.R
import com.jgsilveira.cleanarch.search.impl.domain.model.config.ArgsContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.impl.presentation.SearchUIEvent
import com.jgsilveira.cleanarch.search.impl.presentation.SearchUIState
import com.jgsilveira.cleanarch.search.impl.presentation.SearchViewModel
import com.jgsilveira.cleanarch.search.impl.presentation.model.FeedbackSectionUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.ListSectionUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.SearchResultUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.UIText
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val ARGS = "args"

internal class SearchActivity : ComponentActivity() {
    private val contextConfig: SearchContextConfig by lazy { getArgs()!! }
    private val viewModel by viewModel<SearchViewModel> { parametersOf(contextConfig) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier,
                    topBar = {
                        SearchTopBar()
                    }
                ) { innerPadding ->
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    SearchScreen(
                        uiState = uiState,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        onUiEvent = { uiEvent ->
                            viewModel.dispatchEvent(uiEvent)
                        }
                    )
                }
            }
        }
    }

    private fun getArgs(): SearchContextConfig? {
        val bundle = intent.extras ?: Bundle()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(ARGS, ArgsContextConfig::class.java)
        } else {
            bundle.getParcelable<ArgsContextConfig>(ARGS)
        }
    }

    companion object {

        fun newIntent(
            context: Context,
            args: ArgsContextConfig
        ) = Intent(context, SearchActivity::class.java)
            .putExtra(ARGS, args)
    }
}

@Composable
private fun SearchTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.title_search))
        }
    )
}

@Composable
private fun SearchScreen(
    uiState: SearchUIState,
    modifier: Modifier = Modifier,
    onUiEvent: (SearchUIEvent) -> Unit = {}
) {
    var searchBarQuery by rememberSaveable { mutableStateOf("") }
    Box(modifier = modifier.semantics { isTraversalGroup = true }) {
        TextField(
            value = searchBarQuery,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .align(Alignment.TopCenter),
            onValueChange = { newQuery ->
                searchBarQuery = newQuery
                onUiEvent(SearchUIEvent.SearchBarChange(newQuery))
            }
        )
        val contentModifier = Modifier.align(Alignment.Center).semantics { traversalIndex = 1f }
        when (uiState) {
            SearchUIState.Empty ->
                EmptyContent(modifier = contentModifier)

            is SearchUIState.Error,
            is SearchUIState.Results -> {
                val section = (uiState as? SearchUIState.Results)?.sections ?: emptyList()
                ResultsContent(
                    sections = section,
                    modifier = contentModifier
                )
            }

            SearchUIState.Searching ->
                SearchingContent(modifier = contentModifier)
        }
    }
}

@Composable
private fun SearchingContent(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .height(48.dp),
        )
    }
}

@Composable
private fun EmptyContent(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(
            text = stringResource(R.string.empty_state_message),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ResultsContent(
    sections: List<SearchResultUIData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = sections,
            key = { section -> section.id }
        ) { section ->
            when (section) {
                is ListSectionUIData -> {
                    ListSection(
                        uiData = section,
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }

                is FeedbackSectionUIData -> {
                    FeedbackSection(
                        uiData = section,
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun ListSection(
    uiData: ListSectionUIData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        uiData.title?.let {
            Text(text = it.asString())
        }
        Text(
            text = stringResource(
                R.string.section_items_count,
                uiData.items.size
            )
        )
    }
}

@Composable
private fun FeedbackSection(
    uiData: FeedbackSectionUIData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        uiData.info.title?.let {
            Text(text = it.asString())
        }
        Text(text = uiData.info.message.asString())
    }
}

@Composable
private fun UIText.asString(): String {
    return when (this) {
        is UIText.Plain -> this.value
        is UIText.Resource -> stringResource(this.resId)
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen(
            uiState = SearchUIState.Empty,
            modifier = Modifier.fillMaxSize()
        )
    }
}