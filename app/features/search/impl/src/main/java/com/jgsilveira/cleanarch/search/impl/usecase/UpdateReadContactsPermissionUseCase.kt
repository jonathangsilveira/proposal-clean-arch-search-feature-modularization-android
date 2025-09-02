package com.jgsilveira.cleanarch.search.impl.usecase

import com.jgsilveira.cleanarch.search.impl.domain.repository.UserPermissionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class UpdateReadContactsPermissionUseCase(
    private val repository: UserPermissionRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.Default
) {

    suspend operator fun invoke(isEnabled: Boolean): Result<Unit> {
        return runCatching {
            withContext(coroutineContext) {
                repository.setReadContactsEnabled(isEnabled)
            }
        }
    }
}