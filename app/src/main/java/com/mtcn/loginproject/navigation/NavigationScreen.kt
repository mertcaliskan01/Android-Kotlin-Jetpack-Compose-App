package com.mtcn.loginproject.navigation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mtcn.loginproject.ui.home.Home
import com.mtcn.loginproject.ui.login.Login
import com.mtcn.loginproject.viewmodel.LoginViewModel
import com.mtcn.loginproject.ui.login.Register

@Composable
fun NavigationScreen(viewModel: LoginViewModel) {

    val navController = rememberNavController()
    val loadingProgressBar = viewModel.progressBar.value
    val imageError = viewModel.imageErrorAuth.value

    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {
        composable(route = Destination.Login.route) {
            if (viewModel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.Home.route) {
                        popUpTo(route = Destination.Login.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                Login(
                    navController,
                    loadingProgressBar = loadingProgressBar,
                    onclickLogin = viewModel::login,
                    dismissDialog = viewModel::dismissDialog,
                    imageError = imageError
                )
            }
        }

        composable(route = Destination.Home.route) {
            Home()
        }

        composable(route = Destination.Register.route) {
            Register(
                navController,
                loadingProgressBar = loadingProgressBar,
                onClickRegister = viewModel::register,
                dismissDialog = viewModel::dismissDialog,
                imageError = imageError
            )
        }

    }
}