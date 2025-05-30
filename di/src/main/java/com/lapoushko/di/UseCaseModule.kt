package com.lapoushko.di

import com.lapoushko.domain.repo.RssRepository
import com.lapoushko.domain.usecase.SubscribeUseCaseGetChannel
import com.lapoushko.domain.usecase.SubscribeUseCaseGetChannelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 */
@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSubscribeUseCaseGetRss(repository: RssRepository): SubscribeUseCaseGetChannel {
        return SubscribeUseCaseGetChannelImpl(repository)
    }
}