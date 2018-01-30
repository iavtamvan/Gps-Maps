package com.example.root.gps;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.gps.ServiceGPS.GPSTracker;

public class TrackingLongLat extends AppCompatActivity {

    Button btnShowLoc;
    private static final int REQ_CODE_PERM = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private Button btnLoc;

    GPSTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsTracker = new GPSTracker(TrackingLongLat.this);

                // TODO Cek Lokasi

                if (gpsTracker.canGetLocation()){
                    double lat = gpsTracker.getLatitude();
                    double longitude = gpsTracker.getLongitude();

                    Toast.makeText(TrackingLongLat.this, "Ini Hasilnya : " + lat  + "\n" +
                            longitude, Toast.LENGTH_SHORT).show();

                    Bundle bundle  = new Bundle();// TODO mengirimkan hasil dari JSON ke Tabungan.Activity.jav
                    bundle.putString("lat", String.valueOf(lat));// TODO mengirimkan hasil dari JSON ke Tabungan.Activity.jav
                    bundle.putString("long", String.valueOf(longitude));// TODO mengirimkan hasil dari JSON ke Tabungan.Activity.jav
                    Intent intent = new Intent(getApplicationContext(),MapsActivity.class);// TODO mengirimkan hasil dari JSON ke Tabungan.Activity.jav
                    intent.putExtras(bundle);// TODO mengirimkan hasil dari JSON ke Tabungan.Activity.jav
                    startActivity(intent);// TODO mengirimkan hasil dari JSON ke Tabungan.Activity.jav

                } else {
                    gpsTracker.showSettingsAlert();
                }
            }
        });
    }

    private void initView() {
        btnLoc = (Button) findViewById(R.id.btnLoc);
    }
}
