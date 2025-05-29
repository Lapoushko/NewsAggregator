package com.lapoushko.feature.mapper

import com.lapoushko.domain.entity.Channel
import com.lapoushko.domain.entity.News
import com.lapoushko.feature.model.ChannelItem
import com.lapoushko.feature.model.NewsItem
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface RssItemMapper {
    fun toItemChannel(channel: Channel): ChannelItem
}

class RssItemMapperImpl @Inject constructor() : RssItemMapper {
    override fun toItemChannel(channel: Channel): ChannelItem {
        channel.apply {
            return ChannelItem(
                title = title,
                link = link,
                description = description,
                pubDate = pubDate,
                image = image,
                news = news.map { toItemNews(it) }
            )
        }
    }

    private fun toItemNews(item: News): NewsItem {
        item.apply {
            return NewsItem(
                title = title,
                guid = guid,
                description = description,
                categories = categories,
                pubDate = pubDate,
                creator = creator,
                image = image,
                date = date
            )
        }
    }
}