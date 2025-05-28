package com.lapoushko.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lapoushko.navigation.model.Screen
import com.lapoushko.rss.NewsScreen

/**
 * @author Lapoushko
 */
@Composable
fun BottomNavigationScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screen.News
    ){
        composable<Screen.News> {
            NewsScreen(modifier = modifier)
        }
    }
}