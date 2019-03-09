package com.francislainy.campos.weather.model

import android.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Francislainy on 08/06/2018.
 */
class APIError {

    @SerializedName("success")
    @Expose
    private val success: Boolean? = null

    @SerializedName("err")
    @Expose
    val errorMessage: String? = null


    val errorCode: Int?
        get() = R.attr.name


}
