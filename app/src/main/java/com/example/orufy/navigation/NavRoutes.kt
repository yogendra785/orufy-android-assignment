package com.example.orufy.navigation

sealed class NavRoutes( val route: String){
    object Home: NavRoutes("home")
    object WebView: NavRoutes("webview")
    object History: NavRoutes("history")
}