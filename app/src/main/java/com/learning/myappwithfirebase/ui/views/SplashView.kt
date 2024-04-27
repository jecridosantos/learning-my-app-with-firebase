package com.learning.myappwithfirebase.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.learning.myappwithfirebase.ui.viewmodels.SplashViewModel

@Composable
fun SplashView(navController: NavController, splashViewModel: SplashViewModel) {
    val isLogged = splashViewModel.isLogged
    LaunchedEffect(Unit) {
        if (isLogged) {
            navController.navigate("HomeView")
        } else {
            navController.navigate("LoginView")
        }
    }
}