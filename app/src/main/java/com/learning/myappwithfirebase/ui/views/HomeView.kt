package com.learning.myappwithfirebase.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.learning.myappwithfirebase.ui.viewmodels.HomeViewModel

@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        homeViewModel.getCurrentUser()
    }

    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello!!", fontSize = 24.sp)
        Text(text = homeViewModel.userLogged.email, fontSize = 36.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                homeViewModel.logout {
                        success ->
                    if (success) {
                        navController.navigate("LoginView") {
                            popUpTo("SplashView") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Error on Log Out", Toast.LENGTH_LONG).show()
                    }
                }
            }
        ) {
            Text(text = "Log Out")
        }
    }
}