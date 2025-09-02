package com.jgsilveira.cleanarch.search.impl.data.repository

import com.jgsilveira.cleanarch.search.impl.domain.repository.UserPermissionRepository

internal object InMemoryUserPermissionRepository: UserPermissionRepository {
    private var isReadContactsEnabled = false

    override suspend fun setReadContactsEnabled(isEnabled: Boolean) {
        isReadContactsEnabled = isEnabled
    }

    override suspend fun isReadContactsEnabled(): Boolean {
        return isReadContactsEnabled
    }
}