package com.example.test.main.Task5;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class Multilanguage extends AppCompatActivity {

    MaterialButton en,hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_multilanguage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        en = findViewById(R.id.t5SetLangEn);
        hi = findViewById(R.id.t5SetLangHn);

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLang("en");
                recreate();
            }
        });
        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLang("hi");
                recreate();
            }
        });
    }

    private void setLang(String lang) {
        Locale locale = new Locale(lang);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.locale = locale;
        resources.updateConfiguration(config, dm);
    }
}