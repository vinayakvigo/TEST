package com.example.test.main.Task2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.test.R;
import com.example.test.main.Task2.Fragments.FristFragment;
import com.example.test.main.Task2.Fragments.SecondFragment;
import com.example.test.main.Task2.Fragments.ThirdFragment;
import com.google.android.material.tabs.TabLayout;

public class TabLaout extends AppCompatActivity {

    String FirstName;
    String LastName;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tab_laout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
         FirstName = intent.getStringExtra("FirstName");
         LastName = intent.getStringExtra("LastName");
         ID = intent.getStringExtra("ID");

        //Toast.makeText(this, "Name"+FirstName, Toast.LENGTH_SHORT).show();

        // Pass data to fragments using Bundle
        Bundle bundle = new Bundle();
        bundle.putString("FirstName", FirstName);
        bundle.putString("LastName", LastName);
        bundle.putString("ID", ID);



        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(),bundle));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);







    }
}