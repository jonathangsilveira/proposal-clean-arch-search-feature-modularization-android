package com.jgsilveira.cleanarch.search.impl.presentation

import com.jgsilveira.cleanarch.search.impl.usecase.SearchUseCase
import com.jgsilveira.cleanarch.search.impl.usecase.UpdateReadContactsPermissionUseCase

internal class SearchUseCaseProvider(
    val search: SearchUseCase,
    val updateReadContactsPermission: UpdateReadContactsPermissionUseCase
)