package com.lapoushko.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
@Parcelize
data class RssItem(
    val title: String,
    val link: String,
    val description: String,
    val image: String,
    val creator: String,
    val pubDate: String,
    val categories: List<String>
): Parcelable