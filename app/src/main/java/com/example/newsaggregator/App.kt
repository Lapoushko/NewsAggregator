package com.example.newsaggregator

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lapoushko.navigation.graph.BottomNavigationScreen
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Lapoushko
 */
@HiltAndroidApp
class App : Application() {
    @Composable
    fun Host(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        BottomNavigationScreen(navController, modifier = modifier)
    }
}