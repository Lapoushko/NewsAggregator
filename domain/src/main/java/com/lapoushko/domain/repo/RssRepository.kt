package com.lapoushko.domain.repo

import com.lapoushko.domain.entity.Channel

/**
 * @author Lapoushko
 */
interface RssRepository {
    suspend fun getChannel(): Channel

    suspend fun getCategories(): String
}