package com.francislainy.gists.api

import android.util.Log
import com.francislainy.campos.weather.model.APIError
import com.francislainy.campos.weather.model.WeatherLonAndLat
import com.francislainy.gists.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Francislainy on 08/06/2018.
 */

object WeatherLonAndLatAPI {

    private val LOG_TAG = WeatherLonAndLatAPI.javaClass.simpleName

    interface ThisCallback {

        fun onSuccess(weather_LonAndLat_: WeatherLonAndLat?)

        fun onFailure()

        fun onError(error: APIError)
    }


    /* GET */
    fun postData(callback: ThisCallback) {


        val call = Service.service.getWeather("53.3169915", "-6.269298", "metric", "5d6cc85baeb52f2949033a1818129a4c")


        call.enqueue(object : Callback<WeatherLonAndLat> {


            override fun onResponse(call: Call<WeatherLonAndLat>, response: Response<WeatherLonAndLat>) {

                try {

                    Log.i(LOG_TAG, call.isExecuted.toString())
                    Log.i(LOG_TAG, call.request().toString())
                    Log.i(LOG_TAG, call.isCanceled.toString())

                    if (response.isSuccessful) {

                        Log.i(LOG_TAG, response.body().toString())

                        callback.onSuccess(response.body())

                    } else {

                        // parse the response body...
//                        val error = ErrorUtils.parseError(response)
//                        // ... object to use it to show error information
//
//
//                        Log.e(LOG_TAG, response.raw().toString())
//
//                        callback.onError(error) //todo - 09/03/18
                    }


                } catch (e: Exception) {

                    Log.i(LOG_TAG, call.isExecuted.toString())
                    Log.i(LOG_TAG, call.request().toString())
                    Log.i(LOG_TAG, call.isCanceled.toString())

                    Log.e(LOG_TAG, "Exception: " + e.message)

                    callback.onFailure()
                }

            }

            override fun onFailure(call: Call<WeatherLonAndLat>, t: Throwable) {

                Log.i(LOG_TAG, call.isExecuted.toString())
                Log.i(LOG_TAG, call.request().toString())
                Log.i(LOG_TAG, call.isCanceled.toString())

                Log.e(LOG_TAG, "onFailure: " + t.message)

                callback.onFailure()

            }

        })

    }

}