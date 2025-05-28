package com.lapoushko.domain.usecase

import com.lapoushko.domain.entity.Rss
import com.lapoushko.domain.repo.RssRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeUseCaseGetRss {
    fun getRss(): Flow<Rss>
}

class SubscribeUseCaseGetRssImpl @Inject constructor(
    private val repository: RssRepository
): SubscribeUseCaseGetRss{
    override fun getRss(): Flow<Rss> {
        return repository.getRss()
    }
}