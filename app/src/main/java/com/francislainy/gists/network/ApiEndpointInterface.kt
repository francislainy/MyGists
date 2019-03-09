package com.francislainy.gists.network

import com.francislainy.campos.weather.model.WeatherLonAndLat
import com.francislainy.gists.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpointInterface {

    @GET("/data/2.5/forecast")
    fun getForecast(
        @Query("id") id: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<Forecast>

    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<WeatherLonAndLat>



}
