package com.lapoushko.rss

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lapoushko.feature.model.RssItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class RssScreenViewModel @Inject constructor() : ViewModel() {
    private var _state = MutableRssScreenState()
    val state = _state as RssScreenState

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
        override var news: List<String> by mutableStateOf(emptyList())
        override var tags: Set<String> by mutableStateOf(setOf("a","b","c","d","e","f","g","h"))
        override var selectedTags: Set<String> by mutableStateOf(emptySet())
        override var isSorted: Boolean by mutableStateOf(false)
    }
}

interface RssScreenState {
    val initialNews: List<RssItem>
    val news: List<String>

    val tags: Set<String>
    val selectedTags: Set<String>

    val isSorted: Boolean
}