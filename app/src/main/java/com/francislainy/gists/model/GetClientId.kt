package com.francislainy.gists.model

import com.google.gson.annotations.SerializedName

data class GetClientId(
    @SerializedName("client_id")
    val clientId: Int,
    @SerializedName("success")
    val success: Boolean
)