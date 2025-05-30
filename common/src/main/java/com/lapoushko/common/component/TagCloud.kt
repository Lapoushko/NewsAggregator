package com.lapoushko.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            Chip(
                tag = tag,
                selected = selectedTags.contains(tag),
                onTagSelected = { onTagSelected(it) }
            )
        }
    }
}

@Composable
fun Chip(
    tag: String,
    selected: Boolean,
    onTagSelected: (String) -> Unit
) {
    FilterChip(
        onClick = { onTagSelected(tag) },
        label = { Text(tag) },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        }
    )
}