package com.lapoushko.rss

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lapoushko.domain.entity.Channel
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

    fun loadRss(){
        viewModelScope.launch {
            useCaseRss.getChannel()?.let { channel: Channel ->
                _state.channel = mapper.toItemChannel(channel)
                _state.initialNews = state.channel!!.news
                _state.news = state.initialNews
                _state.statusLoading = RssScreenState.StatusLoading.SUCCESS
            }
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
        sort(state.sortState)
    }

    fun sort(newSortState: RssScreenState.SortState = RssScreenState.SortState.NONE){
        when(newSortState){
            RssScreenState.SortState.DESCENDING -> {
                _state.news = _state.news.sortedByDescending {
                    it.date
                }
            }
            RssScreenState.SortState.ASCENDING -> {
                _state.news = _state.news.sortedBy {
                    it.date
                }
            }
            RssScreenState.SortState.NONE -> _state.news = state.initialNews
        }
        _state.sortState = newSortState
    }

    fun updateStatusLoading(statusLoading: RssScreenState.StatusLoading){
        _state.statusLoading = statusLoading
    }

    private class MutableRssScreenState : RssScreenState {
        override var channel: ChannelItem? by mutableStateOf(null)
        override var initialNews: List<NewsItem> by mutableStateOf(emptyList())
        override var news: List<NewsItem> by mutableStateOf(emptyList())
        override var tags: Set<String> by mutableStateOf(setOf("a","b","c","d","e","f","g","h"))
        override var selectedTags: Set<String> by mutableStateOf(mutableSetOf())
        override var sortState: RssScreenState.SortState by mutableStateOf(RssScreenState.SortState.NONE)
        override var statusLoading: RssScreenState.StatusLoading by mutableStateOf(RssScreenState.StatusLoading.LOADING)
    }
}

interface RssScreenState {
    val channel: ChannelItem?
    val initialNews: List<NewsItem>
    val news: List<NewsItem>

    val tags: Set<String>
    val selectedTags: Set<String>

    val sortState: SortState

    val statusLoading: StatusLoading

    enum class SortState{
        NONE,
        ASCENDING,
        DESCENDING
    }

    enum class StatusLoading{
        LOADING,
        SUCCESS
    }
}