package com.mtcn.loginproject.network.repository

import com.mtcn.loginproject.network.dto.LoginDto
import com.mtcn.loginproject.network.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun getLogin(@Body loginDto: LoginDto) : Response<TokenDto>
}