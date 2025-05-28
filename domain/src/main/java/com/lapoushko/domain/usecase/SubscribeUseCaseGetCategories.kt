package com.lapoushko.domain.usecase

import com.lapoushko.domain.repo.RssRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeUseCaseGetCategories {
    fun getCategories(): Flow<String>
}

class SubscribeUseCaseGetCategoriesImpl @Inject constructor(
    private val repository: RssRepository
): SubscribeUseCaseGetCategories{
    override fun getCategories(): Flow<String> {
        return repository.getCategories()
    }
}