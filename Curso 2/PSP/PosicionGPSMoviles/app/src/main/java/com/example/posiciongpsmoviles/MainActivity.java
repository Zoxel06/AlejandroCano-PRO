package com.example.posiciongpsmoviles;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.*;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // All Location API methods require the ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION permissions

        // Use with getSystemService(Class) to retrieve a LocationManager for controlling location updates.

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // RequestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener)

        // Register for location updates using the named provider, and a pending intent.

        // 3 segundos para volver a pedir la posición

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        String str = "Latitud: " + location.getLatitude() + " Longitud: " + location.getLongitude();

        // Mostrar la latitud y la longitud en un TextView

        ((TextView)findViewById(R.id.TextGPS)).setText(str);

        // Mostrar la latitud y la longitud con Toast

        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

        // GPS desactivado

        Toast.makeText(getBaseContext(), "GPS desactivado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        // GPS activado

        Toast.makeText(getBaseContext(), "GPS activado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}