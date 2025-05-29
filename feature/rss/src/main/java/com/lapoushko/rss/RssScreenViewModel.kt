package com.lapoushko.rss

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lapoushko.domain.usecase.SubscribeUseCaseGetChannel
import com.lapoushko.feature.mapper.RssItemMapper
import com.lapoushko.feature.model.ChannelItem
import com.lapoushko.feature.model.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class RssScreenViewModel @Inject constructor(
    private val useCaseRss: SubscribeUseCaseGetChannel,
    private val mapper: RssItemMapper
) : ViewModel() {
    private var _state = MutableRssScreenState()
    val state = _state as RssScreenState

    init {
        loadRss()
        setTags()
    }

    private fun loadRss(){
        viewModelScope.launch {
            _state.channel = mapper.toItemChannel(useCaseRss.getChannel())
            _state.initialNews = state.channel.news
            _state.news = state.initialNews
        }
    }

    fun setTags(){
        _state.tags = state.news.flatMap { it.categories }.toSet()
    }

    fun cleanTags(){
        _state.selectedTags = emptySet()
        updateNews()
    }

    fun updateTag(tag: String) {
        val currentTags = _state.selectedTags
        val newSelectedTags = if (currentTags.contains(tag)) {
            currentTags - tag
        } else {
            currentTags + tag
        }
        _state.selectedTags = newSelectedTags
        updateNews()
    }

    private fun updateNews(){
        _state.news = if (state.selectedTags.isEmpty()) {
            state.initialNews
        } else {
            state.initialNews.filter { newsItem ->
                newsItem.categories.intersect(state.selectedTags).isNotEmpty()
            }
        }
    }

    fun sort(){
        if (state.isSortedByDescending){
            _state.news = _state.news.sortedByDescending {
                _state.isSortedByDescending = false
                it.date
            }
        }
        else{
            _state.news = _state.news.sortedBy {
                _state.isSortedByDescending = true
                it.date
            }
        }
    }

    private class MutableRssScreenState : RssScreenState {
        override var channel: ChannelItem by mutableStateOf(ChannelItem())
        override var initialNews: List<NewsItem> by mutableStateOf(emptyList())
        override var news: List<NewsItem> by mutableStateOf(emptyList())
        override var tags: Set<String> by mutableStateOf(setOf("a","b","c","d","e","f","g","h"))
        override var selectedTags: Set<String> by mutableStateOf(mutableSetOf())
        override var isSortedByDescending: Boolean by mutableStateOf(false)
    }
}

interface RssScreenState {
    val channel: ChannelItem
    val initialNews: List<NewsItem>
    val news: List<NewsItem>

    val tags: Set<String>
    val selectedTags: Set<String>

    val isSortedByDescending: Boolean
}