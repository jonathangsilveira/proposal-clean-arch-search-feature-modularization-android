package com.jgsilveira.cleanarch.search.model

import com.jgsilveira.cleanarch.search.model.SearchResultSectionDisplay.LIST

data class ListSearchResultSection(
    override val title: String?,
    val items: List<ListItem>
): SearchResultSection {
    override val display = LIST
}
