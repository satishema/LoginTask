package com.example.leeloologin.retrofit

import com.example.leeloologin.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("check-access/by-login-pass")
    fun loginByUserNameAndPassword(
        @Query("_key") apiKey: String,
        @Query("login") username: String,
        @Query("pass") password: String
    ): Call<LoginResponse>

    @GET("check-access/by-email")
    fun loginByUserByEmail(
        @Query("_key") apiKey: String,
        @Query("email") email: String
    ): Call<LoginResponse>

    @GET("check-access/by-login")
    fun checkAccessByUserName(
        @Query("_key") apiKey: String,
        @Query("login") username: String
    ): Call<LoginResponse>
}