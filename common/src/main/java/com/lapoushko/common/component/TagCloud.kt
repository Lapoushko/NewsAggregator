package com.lapoushko.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.lapoushko.common.theme.DarkGray
import com.lapoushko.common.theme.Stroke
import com.lapoushko.common.theme.Typography

/**
 * @author Lapoushko
 */
@Composable
fun TagCloud(
    tags: Set<String>,
    selectedTags: Set<String>,
    onTagSelected: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags.toList()) { tag ->
            val isSelected = selectedTags.contains(tag)
            FilterChip(
                onClick = { onTagSelected(tag) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = DarkGray,
                    labelColor = Stroke
                ),
                selected = isSelected,
                label = { Text(text = tag, style = Typography.bodySmall) }
            )
        }
    }
}