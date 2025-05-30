package com.lapoushko.domain.usecase

import com.lapoushko.domain.entity.Channel
import com.lapoushko.domain.repo.RssRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeUseCaseGetChannel {
    suspend fun getChannel(): Channel?
}

class SubscribeUseCaseGetChannelImpl @Inject constructor(
    private val repository: RssRepository
): SubscribeUseCaseGetChannel{
    override suspend fun getChannel(): Channel? {
        return repository.getChannel()
    }
}