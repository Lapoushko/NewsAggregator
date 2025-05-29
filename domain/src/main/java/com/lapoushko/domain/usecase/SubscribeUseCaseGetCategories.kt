package com.lapoushko.domain.usecase

import com.lapoushko.domain.repo.RssRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeUseCaseGetCategories {
    suspend fun getCategories(): String
}

class SubscribeUseCaseGetCategoriesImpl @Inject constructor(
    private val repository: RssRepository
): SubscribeUseCaseGetCategories{
    override suspend fun getCategories(): String {
        return repository.getCategories()
    }
}