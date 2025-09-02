package com.jgsilveira.cleanarch.search.impl.view.navigation

import android.content.Context
import android.content.Intent
import com.jgsilveira.cleanarch.search.android.navigation.SearchNavigation
import com.jgsilveira.cleanarch.search.android.navigation.SearchOrigin
import com.jgsilveira.cleanarch.search.impl.domain.mapper.SearchContextConfigMapper
import com.jgsilveira.cleanarch.search.impl.view.SearchActivity

internal class SearchNavigator(
    private val contextConfigMapper: SearchContextConfigMapper
): SearchNavigation {

    override fun navigate(
        context: Context,
        searchOrigin: SearchOrigin
    ) {
        val intent = createIntent(context, searchOrigin)
        context.startActivity(intent)
    }

    private fun createIntent(
        context: Context,
        searchOrigin: SearchOrigin
    ): Intent {
        val contextConfig = contextConfigMapper.fromOrigin(searchOrigin)
        return SearchActivity.newIntent(context, contextConfig)
    }
}