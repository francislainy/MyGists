package com.francislainy.gists.model

/**
 * Created by Francislainy on 09/06/2018.
 */

import com.francislainy.campos.weather.model.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListWeather(

    @SerializedName("dt")
    @Expose
    var dt: Int? = null,
    @SerializedName("main")
    @Expose
    var main: Main? = null,
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null,
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null,
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null,
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null,
    @SerializedName("rain")
    @Expose
    var rain: Rain? = null
)