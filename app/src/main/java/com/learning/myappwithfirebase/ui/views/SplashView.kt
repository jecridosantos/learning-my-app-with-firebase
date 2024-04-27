package com.learning.myappwithfirebase.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun SplashView(navController: NavController) {
    val isLogged = remember {
        true
    }

    LaunchedEffect(Unit) {
        if (isLogged) {
            navController.navigate("HomeView")
        } else {
            navController.navigate("LoginView")
        }
    }
}