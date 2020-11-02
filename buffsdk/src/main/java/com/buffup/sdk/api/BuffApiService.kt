package com.buffup.sdk.api

import com.buffup.sdk.model.Buff
import com.buffup.sdk.model.BuffApiResult
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://demo2373134.mockable.io"

private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
                GsonConverterFactory.create(
                        GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                )
        )
        .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
        .build()

interface BuffApiService {
    @GET("/buffs/{buffId}")
    suspend fun loadBuff(@Path("buffId") id: Long): BuffApiResult<Buff>
}

object BuffApi {
    val retrofitService: BuffApiService by lazy { retrofit.create(
            BuffApiService::class.java) }
}