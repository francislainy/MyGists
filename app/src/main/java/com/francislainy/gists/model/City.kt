package com.francislainy.campos.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City (

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null,
    @SerializedName("country")
    @Expose
    var country: String? = null

)
