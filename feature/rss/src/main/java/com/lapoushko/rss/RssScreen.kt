package com.lapoushko.rss

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.lapoushko.common.component.SearchBarRss
import com.lapoushko.common.component.TagCloud
import com.lapoushko.common.theme.DarkGray
import com.lapoushko.common.theme.Stroke
import com.lapoushko.common.theme.Typography
import com.lapoushko.common.theme.horizontalPadding
import com.lapoushko.feature.R
import com.lapoushko.feature.model.NewsItem

/**
 * @author Lapoushko
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: RssScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onToDetail: (String) -> Unit
) {
    val state = viewModel.state
    var query by rememberSaveable { mutableStateOf(state.query) }

    when (state.statusLoading) {
        RssScreenState.StatusLoading.LOADING -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Green)
                Text(
                    modifier = Modifier.clickable { viewModel.loadRss() },
                    text = "Попробовать снова",
                    style = Typography.labelLarge,
                    color = White
                )
            }
        }

        RssScreenState.StatusLoading.SUCCESS -> {
            PullToRefreshBox(
                onRefresh = {
                    viewModel.updateStatusLoading(RssScreenState.StatusLoading.LOADING)
                    viewModel.loadRss()
                },
                isRefreshing = state.statusLoading == RssScreenState.StatusLoading.LOADING
            ) {
                LazyColumn(
                    modifier = modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        SearchBarRss(
                            text = state.query,
                            onClick = {
                                viewModel.searchByName(state.query)
                            },
                            updateQuery = { viewModel.updateQuery(it) }
                        )
                    }
                    item {
                        TagCloud(
                            tags = state.tags,
                            selectedTags = state.selectedTags,
                            onTagSelected = { viewModel.updateTag(it) }
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.clickable { viewModel.cleanTags() },
                                text = "Очистить фильтры",
                                style = Typography.labelLarge,
                                color = White
                            )
                            SortButton(
                                onSort = {
                                    viewModel.sort(
                                        when (state.sortState) {
                                            RssScreenState.SortState.NONE -> RssScreenState.SortState.ASCENDING
                                            RssScreenState.SortState.ASCENDING -> RssScreenState.SortState.DESCENDING
                                            RssScreenState.SortState.DESCENDING -> RssScreenState.SortState.ASCENDING
                                        }
                                    )
                                }
                            )
                        }
                    }
                    items(state.news) { rss ->
                        CardNews(
                            news = rss,
                            onToDetail = { onToDetail(it) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SortButton(onSort: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable { onSort() }) {
            Text(
                text = "По дате добавления",
                style = Typography.labelLarge,
                color = Green
            )
            Icon(
                painter = painterResource(com.lapoushko.common.R.drawable.sort),
                contentDescription = null,
                tint = Green
            )
        }

    }
}

@Composable
private fun CardNews(
    news: NewsItem,
    onToDetail: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp)
            .clickable { onToDetail(news.guid) },
        colors = CardDefaults.cardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImageCard(news = news)
            TextCard(news = news)
        }
    }
}

@Composable
private fun ImageCard(
    news: NewsItem,
) {
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = news.image,
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ChipBlurContent(content = {
                Text(text = news.pubDate, style = Typography.bodySmall, color = White)
            }
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(news.categories) { category ->
                    ChipBlurContent(
                        content = {
                            Text(
                                text = category,
                                style = Typography.bodySmall,
                                color = White
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TextCard(news: NewsItem) {
    Column(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = news.title,
            style = Typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = HtmlCompat.fromHtml(news.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString(),
                    style = Typography.titleMedium,
                    color = White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Row {
                    Text(
                        text = "Подробнее ",
                        style = Typography.bodySmall,
                        color = Green
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = null,
                        tint = Green
                    )
                }
            }
        }
    }
}

@Composable
private fun ChipBlurContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    Box(
        modifier = modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .height(22.dp)
            .clip(shape)
            .background(
                Stroke.copy(alpha = 0.4f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun DetailScreen(guid: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(guid)
        }
    }, update = {
        it.loadUrl(guid)
    })
}

@Preview(showBackground = true)
@Composable
private fun RssItemCardPreview() {
    CardNews(
        news = NewsItem(
            title = "Тайтл",
            guid = "ссылка",
            description = "описание",
            image = "изображение",
            creator = "автор",
            pubDate = "дата публикации",
            categories = listOf("а", "б")
        ),
        onToDetail = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() {
    NewsScreen(
        onToDetail = {}
    )
}