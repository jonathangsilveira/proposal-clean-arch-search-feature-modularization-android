package com.jgsilveira.cleanarch.search.impl.presentation.mapper

import com.jgsilveira.cleanarch.search.impl.presentation.model.FeedbackSectionUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.ListSectionUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.SearchResultUIData
import com.jgsilveira.cleanarch.search.impl.presentation.model.UIText
import com.jgsilveira.cleanarch.search.model.FeedbackSearchResultSection
import com.jgsilveira.cleanarch.search.model.ListSearchResultSection
import com.jgsilveira.cleanarch.search.model.SearchResultSection
import com.jgsilveira.cleanarch.search.model.SearchResultSectionDisplay

internal object SearchResultSectionMapper {
    fun map(source: SearchResultSection): SearchResultUIData? {
        return when (source.display) {
            SearchResultSectionDisplay.LIST ->
                (source as? ListSearchResultSection)?.toUIData()
            SearchResultSectionDisplay.FEEDBACK ->
                (source as? FeedbackSearchResultSection)?.toUIData()
            SearchResultSectionDisplay.CAROUSEL -> null
        }
    }

    private fun ListSearchResultSection.toUIData(): SearchResultUIData {
        return ListSectionUIData(
            items = items.map { item ->
                ListSectionUIData.Item(
                    title = UIText.Plain(item.title),
                    body = item.body?.let { UIText.Plain(it) },
                    footer = item.footer?.let { UIText.Plain(it) }
                )
            },
            title = title?.let { UIText.Plain(it) }
        )
    }

    private fun FeedbackSearchResultSection.toUIData(): SearchResultUIData {
        return FeedbackSectionUIData(
            info = FeedbackSectionUIData.Info(
                message = UIText.Plain(info.message),
                title = info.title?.let { UIText.Plain(it) },
            ),
            title = null
        )
    }
}