package com.example.orufy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.orufy.history.HistoryScreen
import com.example.orufy.home.HomeScreen
import com.example.orufy.webview.WebViewScreen

@Composable
fun AppNavGraph(navController: NavHostController
                ){
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ){
        composable(NavRoutes.Home.route){
            HomeScreen(navController)
        }

        composable(
            route = NavRoutes.WebView.route,
            arguments = listOf(
                navArgument("url")
                { type = NavType.StringType
                }
            )
            ){
            backStackEntry ->
                    val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(navController,url)
        }

        composable(NavRoutes.History.route){
            HistoryScreen(navController)

        }
    }
}