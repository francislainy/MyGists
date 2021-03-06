package com.francislainy.campos.weather.model

/**
 * Created by Francislainy on 10/06/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys(

        @SerializedName("message")
        @Expose
        var message: Double? = null,
        @SerializedName("country")
        @Expose
        var country: String? = null,
        @SerializedName("sunrise")
        @Expose
        var sunrise: Int? = null,
        @SerializedName("sunset")
        @Expose
        var sunset: Int? = null

)