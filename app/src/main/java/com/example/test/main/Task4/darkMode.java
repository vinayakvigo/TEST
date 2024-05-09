package com.example.test.main.Task4;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class darkMode extends AppCompatActivity {

    boolean mode;
    SwitchMaterial switchMaterial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dark_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switchMaterial = findViewById(R.id.t4DarkMode);
        setInitialTask();


        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchMaterial.setThumbResource(R.drawable.ic_dark_mode_24);
                    HandleSharedPreferences(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);



                } else {
                    switchMaterial.setThumbResource(R.drawable.baseline_light_mode_24);
                    HandleSharedPreferences(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
                //recreate();

            }
        });

    }

    private void setInitialTask() {
        SharedPreferences preferences = getSharedPreferences("APP",MODE_PRIVATE);
        mode = preferences.getBoolean("mode",false);
        switchMaterial.setChecked(mode);
        if (mode) {
            switchMaterial.setThumbResource(R.drawable.ic_dark_mode_24);
           // setTheme(R.style.DayNight);

        } else {
            switchMaterial.setThumbResource(R.drawable.baseline_light_mode_24);
           // setTheme(R.style.Light);
        }
       //recreate();

    }

    private void HandleSharedPreferences(boolean mode) {

        SharedPreferences preferences = getSharedPreferences("APP",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("mode",mode);
        editor.commit();

    }
}