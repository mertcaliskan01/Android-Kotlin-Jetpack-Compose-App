package com.mtcn.loginproject.network.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(@SerializedName("token") val accessTokenVerify: String)
