package com.lapoushko.rss

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.lapoushko.common.component.SearchBarRss
import com.lapoushko.common.component.TagCloud
import com.lapoushko.common.theme.DarkGray
import com.lapoushko.common.theme.PlaceholderInputColor
import com.lapoushko.common.theme.Stroke
import com.lapoushko.common.theme.Typography
import com.lapoushko.common.theme.horizontalPadding
import com.lapoushko.feature.R
import com.lapoushko.feature.model.RssItem

/**
 * @author Lapoushko
 */
@Composable
fun NewsScreen(
    viewModel: RssScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.state
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        SearchBarRss()
        TagCloud(tags = state.tags, selectedTags = emptySet(), onTagSelected = {})
        SortButton(onSort = { viewModel.sort() })
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(state.initialNews) { rss ->
                CardNews(
                    rss = rss.copy(pubDate = rss.pubDate.toDate().toCustomString()),
                    onToDetail = {}
                )
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
    rss: RssItem,
    onToDetail: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp)
            .clickable { onToDetail() },
        colors = CardDefaults.cardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ImageCard(rss = rss)
            TextCard(rss = rss)
        }
    }
}

@Composable
private fun ImageCard(
    rss: RssItem,
) {
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = rss.image,
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ChipBlurContent(
                content = {
                    Text(
                        text = rss.title,
                        style = Typography.bodySmall,
                        color = White
                    )
                }
            )
            ChipBlurContent(content = {
                Text(text = rss.pubDate, style = Typography.bodySmall, color = White)
            }
            )
        }
    }
}

@Composable
private fun TextCard(rss: RssItem) {
    Column(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = rss.title, style = Typography.titleMedium)
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = rss.description,
                style = Typography.bodySmall,
                color = PlaceholderInputColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = rss.title, style = Typography.titleMedium, color = White)
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

@Preview(showBackground = true)
@Composable
private fun CourseItemCardPreview() {
    CardNews(
        rss = RssItem(
            title = "Тайтл",
            link = "ссылка",
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
    NewsScreen()
}