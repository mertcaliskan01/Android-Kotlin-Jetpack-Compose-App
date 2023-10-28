package com.mtcn.loginproject.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtcn.loginproject.network.repository.RetrofitHelper
import com.mtcn.loginproject.network.dto.LoginDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val isSuccessLoading = mutableStateOf(value = false)
    val imageErrorAuth = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value = false)
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.value = true
                val authService = RetrofitHelper.getAuthService()
                val responseService = authService.getLogin(LoginDto(email = email, password = password))
                delay(1500L)

                progressBar.value = false
                if (responseService.isSuccessful) {
                    isSuccessLoading.value = true
                    responseService.body()?.let { tokenDto ->
                        Log.d("Logging", "Response TokenDto: $tokenDto")
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        imageErrorAuth.value = true
                        error.close()
                    }
                }

                loginRequestLiveData.postValue(responseService.isSuccessful)
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
                progressBar.value = false
            }
        }
    }
    fun register(firstName : String , lastName: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("Logging", "Error Authentication")

            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
            }
        }
    }
    fun dismissDialog() {
        imageErrorAuth.value = false
    }
}