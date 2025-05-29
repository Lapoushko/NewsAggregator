package com.lapoushko.di

import com.lapoushko.data.mapper.RssDtoMapper
import com.lapoushko.data.mapper.RssDtoMapperImpl
import com.lapoushko.feature.mapper.RssItemMapper
import com.lapoushko.feature.mapper.RssItemMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 */
@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun providesRssDtoMapper(): RssDtoMapper{
        return RssDtoMapperImpl()
    }

    @Provides
    @Singleton
    fun providesRssItemMapper(): RssItemMapper {
        return RssItemMapperImpl()
    }
}