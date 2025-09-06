package com.jgsilveira.cleanarch.search.impl.presentation.model

import java.util.UUID

internal data class FeedbackSectionUIData(
    override val id: String = UUID.randomUUID().toString(),
    val info: Info,
    val title: UIText? = null
): SearchResultUIData {
    data class Info(
        val message: UIText,
        val title: UIText? = null
    )
}
