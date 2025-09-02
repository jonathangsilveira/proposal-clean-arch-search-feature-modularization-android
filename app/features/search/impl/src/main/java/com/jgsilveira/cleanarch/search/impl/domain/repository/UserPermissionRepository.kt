package com.jgsilveira.cleanarch.search.impl.domain.repository

internal interface UserPermissionRepository {
    suspend fun setReadContactsEnabled(isEnabled: Boolean)
    suspend fun isReadContactsEnabled(): Boolean
}