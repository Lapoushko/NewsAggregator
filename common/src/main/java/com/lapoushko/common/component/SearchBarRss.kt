package com.lapoushko.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.lapoushko.common.theme.Typography

/**
 * @author Lapoushko
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarRss(
    placeholder: String = "Поиск новостей",
    text: String,
    updateQuery: (String) -> Unit,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var isActive by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SearchBar(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = {
                        updateQuery(it)
                    },
                    onSearch = {
                        onClick(text)
                        isActive = false
                    },
                    expanded = isActive,
                    onExpandedChange = {
                        isActive = it
                    },
                    placeholder = {
                        Text(text = placeholder, style = Typography.bodyMedium)
                    },
                    leadingIcon = {
                        if (isActive) {
                            CustomIconButton(
                                imageVector = Icons.Outlined.Close,
                                onClick = {
                                    updateQuery("")
                                    isActive = false
                                    onClick(text)
                                }
                            )
                        }

                    },
                    trailingIcon = {
                        CustomIconButton(
                            imageVector = Icons.Filled.Search,
                            onClick = { onClick(text) }
                        )
                    },
                )
            },
            expanded = false,
            onExpandedChange = {},
        ) {}
    }
}

@Composable
private fun CustomIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}