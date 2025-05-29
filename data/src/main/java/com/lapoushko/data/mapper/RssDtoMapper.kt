package com.lapoushko.data.mapper

import com.lapoushko.domain.entity.Channel
import com.lapoushko.domain.entity.News
import com.lapoushko.network.dto.ItemDto
import com.lapoushko.network.dto.RssDto
import java.time.Instant
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface RssDtoMapper {
    fun toDomainChannel(rssDto: RssDto): Channel
}

class RssDtoMapperImpl @Inject constructor() : RssDtoMapper {
    override fun toDomainChannel(rssDto: RssDto): Channel {
        rssDto.apply {
            return Channel(
                title = channel.title,
                link = channel.link,
                description = channel.description,
                pubDate = channel.pubDate,
                image = channel.image.url,
                news = channel.items.map { toDomainNews(it) },
            )
        }
    }

    private fun toDomainNews(item: ItemDto): News {
        item.apply {
            return News(
                title = title,
                guid = guid,
                description = description,
                categories = categories.map { it.value },
                pubDate = pubDate,
                creator = dcCreator,
                image = contents.maxBy { it.width?.toIntOrNull() ?: 0 }.url,
                date = dcDate.dateStringToLong()
            )
        }
    }

    private fun String.dateStringToLong(): Long {
        val instant = Instant.parse(this)
        return instant.toEpochMilli()
    }
}