package com.example.test.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.example.test.main.Task2.task_tow;
import com.example.test.main.Task3.services;
import com.google.android.material.button.MaterialButton;

public class Task_list extends AppCompatActivity {
    MaterialButton task1,task2,task3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SetUpId();
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Task_list.this,MainActivity.class));
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Task_list.this, task_tow.class));
            }
        });

        task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Task_list.this, services.class));
            }
        });


    }

    public void SetUpId() {
        task1 = findViewById(R.id.t1);
        task2 = findViewById(R.id.t2);
        task3 = findViewById(R.id.t3);
    }
}