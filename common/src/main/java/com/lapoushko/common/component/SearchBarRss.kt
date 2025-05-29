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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    text: String? = null,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    queryReturn: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf(text ?: "") }
    var isActive by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(query) {
        queryReturn(query)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SearchBar(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = {
                        query = it
                    },
                    onSearch = {
                        onClick(query)
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
                                    query = ""
                                    isActive = false
                                    onClick(query)
                                }
                            )
                        }

                    },
                    trailingIcon = {
                        CustomIconButton(
                            imageVector = Icons.Filled.Search,
                            onClick = { onClick(query) }
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