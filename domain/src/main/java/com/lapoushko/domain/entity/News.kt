package com.lapoushko.domain.entity

/**
 * @author Lapoushko
 */
class Channel(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val image: String,
    val news: List<News>,
)

class News(
    val title: String,
    val guid: String,
    val description: String,
    val categories: List<String>,
    val pubDate: String,
    val date: Long,
    val creator: String,
    val image: String
)