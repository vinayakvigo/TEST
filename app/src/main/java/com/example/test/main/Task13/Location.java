package com.example.test.main.Task13;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;


import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Location extends AppCompatActivity {

    private TextView deviceNameTextView;
    private TextView batteryPercentageTextView;


    TextView locationTextView,timeTextView;
    MaterialButton Btn1,Btn3;

    private Handler handler;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        handler = new Handler();

        locationTextView = findViewById(R.id.t13_text);
        Btn1 = findViewById(R.id.t13Btn2);
        Btn3 = findViewById(R.id.t13Btn3);
        timeTextView = findViewById(R.id.t13timeTextView);
        Btn1.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocationService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                startForegroundService(intent);
            }
            startService(intent);
        });

        Btn3.setOnClickListener(v -> {startActivity(new Intent(this, LocationList.class));});



        deviceNameTextView = findViewById(R.id.deviceNameTextView);
        batteryPercentageTextView = findViewById(R.id.batteryPercentageTextView);

        // Get and display device name
        String deviceName = Build.MODEL;
        deviceNameTextView.setText("Device Name: " + deviceName);

        // Register receiver for battery percentage updates
        registerReceiver(batteryPercentageReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //updateCurrentTime();
       // fetchServerTime();
        new InternetTimeGetter(timeTextView).execute();

        CheckLocationPermission();
        getCurrentLocation();



    }


    private void updateCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        timeTextView.setText("Current Time: " + currentTime);
    }

    private void fetchServerTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://worldtimeapi.org/api/timezone/Asia/Kolkata"); // Use the correct API endpoint for Indian time
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    String serverTime = parseServerTime(response.toString());
                    updateServerTimeTextView(serverTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("TimeError", "run: "+e);
                    runOnUiThread(new Runnable() { // Since we're updating UI, use runOnUiThread to show Toast
                        @Override
                        public void run() {
                            Toast.makeText(Location.this, "Failed to fetch server time", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private String parseServerTime(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("datetime");
        } catch (JSONException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    private void updateServerTimeTextView(final String serverTime) {
        runOnUiThread(new Runnable() { // Ensure UI updates are done on the main thread
            @Override
            public void run() {
                timeTextView.setText("Server Time: " + serverTime);
            }
        });
    }


    // BroadcastReceiver to listen for battery percentage updates
    private BroadcastReceiver batteryPercentageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPercentage = level / (float) scale * 100;
            batteryPercentageTextView.setText("Battery Percentage: " + batteryPercentage + "%");
        }
    };

    private void CheckLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            getCurrentLocation();
        }
    }

    private void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "give location permission", Toast.LENGTH_SHORT).show();
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        // Use the latitude and longitude
                        Toast.makeText(this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_SHORT).show();
                   locationTextView.setText(" current location \n Latitude: " + latitude + ", Longitude: " + longitude);

                    } else {
                        // Location is null, handle error
                        Toast.makeText(this, "Unable to retrieve location", Toast.LENGTH_SHORT).show();
                    }
                });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.getLoation) {
            // Handle search action
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);

            }
            Toast.makeText(this, "location clicked clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Add more cases for other menu items if needed

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister receiver when the activity is destroyed
        unregisterReceiver(batteryPercentageReceiver);
    }
}