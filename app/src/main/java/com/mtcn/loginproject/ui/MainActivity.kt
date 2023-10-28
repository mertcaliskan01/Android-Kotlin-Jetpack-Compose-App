package com.mtcn.loginproject.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mtcn.loginproject.navigation.NavigationScreen
import com.mtcn.loginproject.ui.theme.LoginComposeRetrofit2Theme
import com.mtcn.loginproject.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        if (preferences.contains("checked")) {

            if (preferences.getBoolean("checked", true)) {
                launchAppIntro()
            }
        } else {
            launchAppIntro()
        }


        setContent {
            LoginComposeRetrofit2Theme {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationScreen(viewModel = viewModel)
                }
            }
        }
    }

    private fun launchAppIntro() {
        val intent = Intent(this, MainWalkthroughtActivity::class.java)
        startActivity(intent)
    }
}