package com.mtcn.loginproject.navigation

sealed class Destination(val route: String) {
    object Login : Destination(route = "login")
    object Home : Destination(route = "home")
    object Register : Destination(route = "register")

    companion object {
        fun getStartDestination() = Login.route
    }
}
