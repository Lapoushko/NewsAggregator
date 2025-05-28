package com.lapoushko.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lapoushko.common.theme.LightGray
import com.lapoushko.common.theme.PlaceholderInputColor
import com.lapoushko.common.theme.Typography
import com.lapoushko.common.theme.White

/**
 * @author Lapoushko
 */
@Composable
fun SearchBarRss(){
    val height = 60.dp
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .weight(0.5f),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Поиск новостей",
                    style = Typography.bodyMedium,
                    color = PlaceholderInputColor
                )
            },
            leadingIcon = {
                IconButton(onClick = {}, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = White
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGray,
                unfocusedContainerColor = LightGray,
                disabledContainerColor = LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(28.dp),
        )
    }
}