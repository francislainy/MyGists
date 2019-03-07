package com.francislainy.gists.api

import android.util.Log
import com.francislainy.gists.model.GetClientId
import com.francislainy.gists.network.Service
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GetClientIdAPI {

    private val LOG_TAG = "GetClientIdAPI"

    interface ThisCallback {

        fun onSuccess(clientId: GetClientId)

        fun onFailure(failureMessage: String)

        fun onError(errorMessage: String)
    }

    /* POST */
    fun postData(jo: JsonObject, callback: GetClientIdAPI.ThisCallback) {
        val call = Service.getService().get_client_id(jo)
        call.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                Log.e(LOG_TAG, response.body()!!.toString())

                try {
                    if (response.body()!!.get("success").asBoolean) {

                        Log.e(LOG_TAG, "success")

                        val clientId = response.body()!!.get("client_id").asString

                        callback.onSuccess(clientId)

                    } else {
                        Log.e(LOG_TAG, "else")

                        val error = response.body()!!.get("err").asString

                        callback.onError(error)
                    }


                } catch (e: Exception) {
                    Log.e(LOG_TAG, "exception" + e.localizedMessage)

                    callback.onFailure(e.message)
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure: " + t.message)

                callback.onFailure(t.message)

            }
        })

    }

}