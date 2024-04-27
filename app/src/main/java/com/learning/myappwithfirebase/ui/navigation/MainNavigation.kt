package com.learning.myappwithfirebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learning.myappwithfirebase.ui.viewmodels.SplashViewModel
import com.learning.myappwithfirebase.ui.views.HomeView
import com.learning.myappwithfirebase.ui.views.LoginView
import com.learning.myappwithfirebase.ui.views.SplashView

@Composable
fun MainNavigation(splashViewModel: SplashViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "SplashView") {
        composable("SplashView") {
            SplashView(navController, splashViewModel)
        }

        composable("HomeView") {
            HomeView(navController)
        }

        composable("LoginView") {
            LoginView(navController)
        }
    }
}