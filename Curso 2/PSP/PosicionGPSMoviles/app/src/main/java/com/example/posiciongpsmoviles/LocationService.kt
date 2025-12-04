package com.example.posiciongpsmoviles

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class LocationService : Service() {

    private lateinit var locationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val repo = FirebaseRepo()

    override fun onCreate() {
        super.onCreate()
        locationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation ?: return

                val locationData = UserLocation(
                    userId = "user123",   // generar dinámicamente
                    name = "Juan",
                    lat = loc.latitude,
                    lon = loc.longitude
                )

                repo.sendLocation(locationData)
            }
        }

        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build()

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) return

        locationClient.requestLocationUpdates(request, locationCallback, null)
    }

    override fun onBind(p0: Intent?): IBinder? = null
}
