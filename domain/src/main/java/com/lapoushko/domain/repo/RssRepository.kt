package com.lapoushko.domain.repo

import com.lapoushko.domain.entity.Rss
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface RssRepository {
    fun getRss(): Flow<Rss>

    fun getCategories(): Flow<String>
}

class RssRepositoryImpl @Inject constructor(): RssRepository{
    override fun getRss(): Flow<Rss> {
        return flow {  }
    }

    override fun getCategories(): Flow<String> {
        return flow {  }
    }
}