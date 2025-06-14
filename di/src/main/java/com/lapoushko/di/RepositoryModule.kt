package com.lapoushko.di

import com.lapoushko.data.RssRepositoryImpl
import com.lapoushko.domain.repo.RssRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Lapoushko
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRssRepository(
        repository: RssRepositoryImpl
    ): RssRepository
}