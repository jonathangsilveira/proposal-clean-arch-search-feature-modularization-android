package com.jgsilveira.cleanarch.search.impl.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfigModel
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.impl.presentation.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class SearchActivity : AppCompatActivity() {
    private val contextConfig: SearchContextConfig by lazy { getArgs()!! }
    private val viewModel by viewModel<SearchViewModel> { parametersOf(contextConfig) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    private fun getArgs(): SearchContextConfig? {
        val bundle = intent.extras ?: Bundle()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable("args", SearchContextConfigModel::class.java)
        } else {
            bundle.getParcelable<SearchContextConfigModel>("args")
        }
    }

    companion object {

        fun newIntent(
            context: Context,
            contextConfig: SearchContextConfigModel
        ) = Intent(context, SearchActivity::class.java)
            .putExtra("args", contextConfig)
    }
}