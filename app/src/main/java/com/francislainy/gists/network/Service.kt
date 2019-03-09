package com.francislainy.gists.network

import com.francislainy.gists.network.Service.provideRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

object Service {

    val service: ApiEndpointInterface
        get() {
            val baseUrl = "http://api.openweathermap.org/"

            return provideRetrofit(baseUrl).create(ApiEndpointInterface::class.java)
        }

    private fun provideRetrofit(url: String): Retrofit {

        val gson = GsonBuilder().serializeNulls().create()

        val httpClient = OkHttpClient.Builder()

        val builder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))

        return builder.client(httpClient.build())
            .build()
    }
}

