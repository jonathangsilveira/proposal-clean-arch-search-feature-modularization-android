package com.jgsilveira.cleanarch.search.impl.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jgsilveira.cleanarch.search.impl.domain.model.config.DefaultSearchContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
internal class SearchActivity : AppCompatActivity() {
    private val contextConfig: SearchContextConfig by lazy { getArgs()!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    private fun getArgs(): SearchContextConfig? {
        val bundle = intent.extras ?: Bundle()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable("args", DefaultSearchContextConfig::class.java)
        } else {
            bundle.getParcelable<DefaultSearchContextConfig>("args")
        }
    }

    companion object {

        fun newIntent(
            context: Context,
            contextConfig: DefaultSearchContextConfig
        ) = Intent(context, SearchActivity::class.java)
            .putExtra("args", contextConfig)
    }
}