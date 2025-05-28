package com.lapoushko.di

import com.lapoushko.domain.repo.RssRepository
import com.lapoushko.domain.usecase.SubscribeUseCaseGetCategories
import com.lapoushko.domain.usecase.SubscribeUseCaseGetCategoriesImpl
import com.lapoushko.domain.usecase.SubscribeUseCaseGetRss
import com.lapoushko.domain.usecase.SubscribeUseCaseGetRssImpl
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
    fun provideSubscribeUseCaseGetRss(repository: RssRepository): SubscribeUseCaseGetRss {
        return SubscribeUseCaseGetRssImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeUseCaseGetCategories(repository: RssRepository): SubscribeUseCaseGetCategories {
        return SubscribeUseCaseGetCategoriesImpl(repository)
    }
}