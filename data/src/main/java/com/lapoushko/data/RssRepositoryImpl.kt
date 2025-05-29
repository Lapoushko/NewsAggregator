package com.lapoushko.data

import com.lapoushko.data.mapper.RssDtoMapper
import com.lapoushko.domain.entity.Channel
import com.lapoushko.domain.repo.RssRepository
import com.lapoushko.network.RssFeed
import javax.inject.Inject

/**
 * @author Lapoushko
 */
class RssRepositoryImpl @Inject constructor(
    private val rssFeed: RssFeed,
    private val mapper: RssDtoMapper
): RssRepository {
    override suspend fun getChannel(): Channel {
        return mapper.toDomainChannel(rssFeed.getRss())
    }

    override suspend fun getCategories(): String {
        return ""//TODO
    }
}