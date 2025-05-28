package com.lapoushko.rss

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lapoushko.feature.model.RssItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class RssScreenViewModel @Inject constructor() : ViewModel() {
    private var _state = MutableRssScreenState()
    val state = _state as RssScreenState

    fun sort(){
        if (state.isSortedByDescending){
            _state.news = _state.news.sortedByDescending {
                _state.isSortedByDescending = false
                it.publishDate.toDate()
            }
        }
        else{
            _state.news = _state.news.sortedBy {
                _state.isSortedByDescending = true
                it.publishDate.toDate()
            }
        }
    }

    private class MutableRssScreenState : RssScreenState {
        override var initialNews: List<RssItem> by mutableStateOf(
            listOf(
                RssItem(
                    title = "Тайтл",
                    link = "ссылка",
                    description = "описание",
                    image = "изображение",
                    author = "автор",
                    publishDate = "дата публикации",
                    categories = listOf("а", "б")
                )
            )
        )
        override var news: List<RssItem> by mutableStateOf(emptyList())
        override var tags: Set<String> by mutableStateOf(setOf("a","b","c","d","e","f","g","h"))
        override var selectedTags: Set<String> by mutableStateOf(emptySet())
        override var isSortedByDescending: Boolean by mutableStateOf(false)
    }
}

internal fun String.toDate(): LocalDate {
    try {
        val localDate = LocalDate.parse(this)
        return localDate
    } catch (e: Exception){
        return LocalDate.MIN
    }
}

internal fun LocalDate.toCustomString(): String {
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
    val formattedDate: String = this.format(outputFormatter)

    return formattedDate.split(" ")
        .joinToString(" ") {
            if (it.length > 1) it.replaceFirstChar { char -> char.uppercase() } else it
        }
}

interface RssScreenState {
    val initialNews: List<RssItem>
    val news: List<RssItem>

    val tags: Set<String>
    val selectedTags: Set<String>

    val isSortedByDescending: Boolean
}