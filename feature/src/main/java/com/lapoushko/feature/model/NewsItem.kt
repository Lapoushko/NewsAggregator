package com.lapoushko.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */

@Serializable
@Parcelize
data class ChannelItem(
    val title: String = "",
    val link: String = "",
    val description: String = "",
    val pubDate: String = "",
    val image: String = "",
    val news: List<NewsItem> = emptyList(),
): Parcelable

@Serializable
@Parcelize
data class NewsItem(
    val title: String = "",
    val guid: String = "",
    val description: String = "",
    val categories: List<String> = emptyList(),
    val pubDate: String = "",
    val date: Long = 0,
    val creator: String = "",
    val image: String = ""
): Parcelable