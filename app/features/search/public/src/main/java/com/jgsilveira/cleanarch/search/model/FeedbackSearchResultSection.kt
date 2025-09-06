package com.jgsilveira.cleanarch.search.model

import com.jgsilveira.cleanarch.search.model.SearchResultSectionDisplay.FEEDBACK

data class FeedbackSearchResultSection(
    override val title: String?,
    val info: FeedbackInfo
): SearchResultSection {
    override val display = FEEDBACK
}
