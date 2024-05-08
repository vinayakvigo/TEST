package com.example.test.main.Task3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;
import android.app.ActivityManager;


public class services extends AppCompatActivity {

    MaterialButton start ,stop,startBg ,stopBg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sevices);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        start = findViewById(R.id.t3StartService);
        stop = findViewById(R.id.t3StopService);
        startBg = findViewById(R.id.t32StartService);
        stopBg = findViewById(R.id.t32StopService);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(services.this,MyService.class));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(services.this,MyService.class));
            }
        });

        startBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent serviceIntent = new Intent(services.this, ForgroundService.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        startForegroundService(serviceIntent);
                    }
                    startService(serviceIntent);

                }catch (Error e){
                    Log.i("TAG", "onClick: "+e.toString());

                }

            }
        });
        stopBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(services.this,ForgroundService.class));
            }
        });

    }
}