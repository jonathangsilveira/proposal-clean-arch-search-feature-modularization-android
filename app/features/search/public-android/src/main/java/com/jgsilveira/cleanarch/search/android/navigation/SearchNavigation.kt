package com.jgsilveira.cleanarch.search.android.navigation

import android.content.Context

interface SearchNavigation {
    fun navigate(context: Context, searchOrigin: SearchOrigin)
}