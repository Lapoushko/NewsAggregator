package com.lapoushko.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lapoushko.network.RssFeed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Lapoushko
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.theguardian.com")
        .addConverterFactory(
            XML.asConverterFactory(
                "application/xml; charset=UTF8".toMediaType()
            )
        ).build()

    @Provides
    @Singleton
    fun provideRssFeed(retrofit: Retrofit): RssFeed = retrofit.create(RssFeed::class.java)
}