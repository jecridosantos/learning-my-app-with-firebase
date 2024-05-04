package com.learning.myappwithfirebase.ui.views

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.learning.myappwithfirebase.ui.viewmodels.LoginViewModel
import com.learning.myappwithfirebase.utils.Constants

@Composable
fun LoginView(navController: NavController, loginViewModel: LoginViewModel) {

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)
                loginViewModel.loginWithGoogle(account.idToken!!) { success ->
                    if (success) {
                        navController.navigate("HomeView") {
                            popUpTo("SplashView") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Error on Sign In with Google", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } catch (e: Exception) {
                Log.d("Login Google error", "error: $e")
            }
        }

    val googleSignInIntent = fun(): Intent {
        val options = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(Constants.GOOGLE_TOKEN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, options)
        return googleSignInClient.signInIntent
    }


    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome", fontSize = 36.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginViewModel.email,
            onValueChange = { loginViewModel.email = it },
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginViewModel.password,
            onValueChange = { loginViewModel.password = it },
            placeholder = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                loginViewModel.login { success ->
                    if (success) {
                        navController.navigate("HomeView") {
                            popUpTo("SplashView") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Error on Sign In", Toast.LENGTH_LONG).show()
                    }
                }
            }
        ) {
            Text(text = "Sign In")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { launcher.launch(googleSignInIntent()) }
        ) {
            Text(text = "Sign In with Google")
        }
    }


}