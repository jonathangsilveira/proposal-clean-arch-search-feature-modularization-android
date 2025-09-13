@file:OptIn(ExperimentalMaterial3Api::class)

package com.jgsilveira.cleanarch.feature_b.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgsilveira.cleanarch.search.factory.SearcherFactory
import com.jgsilveira.cleanarch.search.model.SearchContext
import com.jgsilveira.cleanarch.search.model.SearchResultSection
import org.koin.android.ext.android.get

class FeatureBActivity internal constructor() : ComponentActivity() {
    private val searcher by lazy {
        get<SearcherFactory>().createSearcher(SearchContext.FEATURE_B)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                FeatureBScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavClick = { onBackPressedDispatcher.onBackPressed() },
                    onSearchResult = { query -> searcher.search(query) }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, FeatureBActivity::class.java)
        }
    }
}

@Composable
private fun FeatureBTopBar(onNavClick: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onNavClick,
                modifier = Modifier.padding(8.dp).wrapContentSize()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        title = {
            Text(text = stringResource(R.string.title_feature_b))
        }
    )
}

@Composable
private fun FeatureBScreen(
    modifier: Modifier = Modifier,
    onNavClick: () -> Unit = {},
    onSearchResult: suspend (query: String) -> List<SearchResultSection> = { emptyList() }
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            FeatureBTopBar(onNavClick = onNavClick)
        }
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            runCatching {
                onSearchResult("vit")
            }.onSuccess { sections ->
                println("Number of results: ${sections.size}")
            }.onFailure {
                println("Something went wrong: ${it.message.orEmpty()}")
            }
        }
        FeatureBContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onNavClick = onNavClick
        )
    }
}

@Composable
private fun FeatureBContent(
    modifier: Modifier = Modifier,
    onNavClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Button(
            onClick = onNavClick,
            modifier = Modifier.wrapContentSize()
        ) {
            Text(text = stringResource(R.string.back))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FeatureBScreenPreview() {
    MaterialTheme {
        FeatureBScreen(Modifier.fillMaxSize())
    }
}