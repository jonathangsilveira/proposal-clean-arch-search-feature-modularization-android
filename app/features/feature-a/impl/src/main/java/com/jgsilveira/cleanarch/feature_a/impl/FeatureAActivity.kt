package com.jgsilveira.cleanarch.feature_a.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgsilveira.cleanarch.feature_b.impl.FeatureBActivity
import com.jgsilveira.cleanarch.search.android.navigation.SearchNavigation
import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import org.koin.android.ext.android.inject

class FeatureAActivity internal constructor(): ComponentActivity() {
    private val searchNavigation by inject<SearchNavigation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FeatureAScreen(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        onSearchClick = { navigateToSearch() },
                        onFeatureBClick = { navigateToFeatureB() }
                    )
                }
            }
        }
    }

    private fun navigateToSearch() {
        searchNavigation.navigate(
            context = this,
            searchOrigin = SearchOrigin.FEATURE_A
        )
    }

    private fun navigateToFeatureB() {
        val intent = FeatureBActivity.newIntent(this)
        startActivity(intent)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, FeatureAActivity::class.java)
        }
    }
}

@Composable
fun FeatureAScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    onFeatureBClick: () -> Unit = {}
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
            onClick = onSearchClick,
            modifier = Modifier.wrapContentSize()
        ) {
            Text(text = stringResource(R.string.search))
        }
        Button(
            onClick = onFeatureBClick,
            modifier = Modifier.wrapContentSize()
        ) {
            Text(text = stringResource(R.string.feature_b))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeatureAScreenPreview() {
    MaterialTheme {
        FeatureAScreen(Modifier.fillMaxSize())
    }
}