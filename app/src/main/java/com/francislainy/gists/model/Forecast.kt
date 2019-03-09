package com.francislainy.gists.model

/**
 * Created by Francislainy on 09/06/2018.
 */

import com.francislainy.campos.weather.model.City
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecast(

    @SerializedName("cod")
    @Expose
    var cod: String? = null,
    @SerializedName("message")
    @Expose
    var message: Double? = null,
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null,
    @SerializedName("listWeather")
    @Expose
    var listWeather: List<ListWeather>? = null,
    @SerializedName("city")
    @Expose
    var city: City? = null
)