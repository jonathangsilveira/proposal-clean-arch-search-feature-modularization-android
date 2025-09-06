package com.jgsilveira.cleanarch.search.impl.presentation.model

import java.util.UUID

internal data class ListSectionUIData(
    override val id: String = UUID.randomUUID().toString(),
    val items: List<Item>,
    val title: UIText? = null
): SearchResultUIData {
    data class Item(
        val title: UIText,
        val body: UIText? = null,
        val footer: UIText? = null
    )
}
