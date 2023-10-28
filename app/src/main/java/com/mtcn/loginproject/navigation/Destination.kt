package com.mtcn.loginproject.navigation

sealed class Destination(val route: String) {
    object Login : Destination(route = "login")
    object Home : Destination(route = "home")
    object Register : Destination(route = "register")
    object ForgotPassword : Destination(route = "password")

    companion object {
        fun getStartDestination() = Login.route
    }
}
