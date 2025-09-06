package com.jgsilveira.cleanarch.search.impl.presentation.mapper

import com.jgsilveira.cleanarch.search.impl.R
import com.jgsilveira.cleanarch.search.impl.presentation.model.FeedbackSectionUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.SearchResultUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.UIText

internal object SearchErrorMapper {
    fun map(throwable: Throwable): SearchResultUIData {
        return FeedbackSectionUIData(
            info = FeedbackSectionUIData.Info(
                message = UIText.Resource(R.string.error_generic_message),
                title = UIText.Resource(R.string.error_generic_title)
            ),
            title = null
        )
    }
}