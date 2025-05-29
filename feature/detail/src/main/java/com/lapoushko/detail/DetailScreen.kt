package com.lapoushko.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

/**
 * @author Lapoushko
 */

@Composable
fun DetailScreen(guid: String){
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = {webView ->
            webView.loadUrl(guid)
        }
    )
}