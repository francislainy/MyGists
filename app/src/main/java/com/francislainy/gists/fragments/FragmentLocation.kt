package com.francislainy.gists.fragments

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.francislainy.campos.weather.model.APIError
import com.francislainy.campos.weather.model.WeatherLonAndLat
import com.francislainy.gists.R
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.api.WeatherLonAndLatAPI
import com.francislainy.gists.util.FRAG_LOCATION
import kotlinx.android.synthetic.main.fragment_location.*

private var LOG_TAG = "FragLocation"
private var ACCESS_LOCATION = 123

class FragmentLocation : Fragment() {

    private var location: Location? = null
//    private var tvLocation: TextView? = null // Adding it here instead of using extension to avoid NPE

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).displayToolbar(FRAG_LOCATION)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        tvLocation = view.findViewById(R.id.tvLocation)

        loadWeather()

        checkPermission()
    }

    private fun loadWeather() {

        WeatherLonAndLatAPI.postData(object : WeatherLonAndLatAPI.ThisCallback {

            override fun onSuccess(w: WeatherLonAndLat?) {

                Log.e(LOG_TAG, w?.coord?.lat.toString())


//                updateUI(weather_LonAndLat_!!)

                Toast.makeText(activity, w?.weather?.get(0)?.main, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure() {

                Log.e(LOG_TAG, "onFailure")
            }

            override fun onError(error: APIError) {

                Log.e(LOG_TAG, "Exception: " + error.errorMessage!!)

//                toast(" error ----")
            }
        })
    }

//    private fun updateUI(weather_LonAndLat_: WeatherLonAndLat) {
//
//        Toast.makeText(applicationContext, weather_LonAndLat_.weather?.get(0)?.description, Toast.LENGTH_LONG).show()
//
//        val weather = weather_LonAndLat_.weather?.get(0)?.description
//
//        when {
//
//            weather?.contains("sunny")!! -> iv_weather.setImageResource(R.drawable.sunny)
//
//            weather.contains("scattered clouds") -> iv_weather.setImageResource(scattered_clouds)
//
//            weather.contains("light rain") -> iv_weather.setImageResource(rainy)
//
//            else -> iv_weather.setImageResource(heavy_snow)
//
//        }
//
////        Log.i(LOG_TAG, weather_LonAndLat_?.weather?.get(0))
//
//        tv_weather.text = weather_LonAndLat_.weather?.get(0)?.description
//    }

    /**
     * LOCATION
     */
    private fun checkPermission() {

        val context = activity as MainActivity
        if (SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
                requestPermissions(arrayOf(ACCESS_FINE_LOCATION), ACCESS_LOCATION)
                return
            }
        }

        getUserLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            ACCESS_LOCATION -> {

                if (grantResults[0] == PERMISSION_GRANTED) {
                    getUserLocation()
                } else {
                    Toast.makeText(activity, "We cannot access to it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getUserLocation() {

        val myLocation = MyLocationListener()

        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ContextCompat.checkSelfPermission(activity as MainActivity, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)
        }
    }

    inner class MyLocationListener : LocationListener {

        init {
            location = Location("Start")
            location!!.longitude = 0.0
            location!!.latitude = 0.0
        }

        override fun onLocationChanged(p0: Location?) {
            location = p0

            tvLocation?.text = location?.latitude.toString()
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

        override fun onProviderEnabled(p0: String?) {}

        override fun onProviderDisabled(p0: String?) {}
    }

}