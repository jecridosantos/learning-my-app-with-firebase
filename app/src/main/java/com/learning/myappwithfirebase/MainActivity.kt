package com.learning.myappwithfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.learning.myappwithfirebase.ui.navigation.MainNavigation
import com.learning.myappwithfirebase.ui.theme.MyAppWithFirebaseTheme
import com.learning.myappwithfirebase.ui.viewmodels.HomeViewModel
import com.learning.myappwithfirebase.ui.viewmodels.LoginViewModel
import com.learning.myappwithfirebase.ui.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel by viewModels()
        val loginViewModel: LoginViewModel by viewModels()
        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            MyAppWithFirebaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(splashViewModel, loginViewModel, homeViewModel)
                }
            }
        }
    }
}
