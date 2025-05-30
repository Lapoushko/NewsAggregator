package com.lapoushko.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lapoushko.detail.DetailScreen
import com.lapoushko.navigation.model.Screen
import com.lapoushko.navigation.util.StringNavType
import com.lapoushko.rss.NewsScreen
import kotlin.reflect.typeOf

/**
 * @author Lapoushko
 */
@Composable
fun BottomNavigationScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.News
    ) {
        composable<Screen.News> {
            NewsScreen(
                modifier = modifier,
                onToDetail = { navController.navigate(Screen.Detail(it)) })
        }
        composable<Screen.Detail>(
            typeMap = mapOf(typeOf<String>() to StringNavType)
        ) { backStackEntry ->
            val guid = backStackEntry.toRoute<Screen.Detail>().guid
            DetailScreen(
                guid = guid
            )
        }
    }
}