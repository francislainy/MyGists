package com.francislainy.campos.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rain (

    @SerializedName("3h")
    @Expose
    var threeHour: Double? = null

)