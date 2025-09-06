package com.jgsilveira.cleanarch.search.impl.presentation.model

import androidx.annotation.StringRes

internal sealed interface UIText {
    data class Plain(val value: String): UIText
    data class Resource(@StringRes val resId: Int): UIText
}