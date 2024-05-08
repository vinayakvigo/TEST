package com.example.test.main.Task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class task_tow extends AppCompatActivity {
    TextInputEditText FirstName,LastName,ID;
    MaterialButton Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_tow);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SetUpId();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FirstName.getText().toString().isEmpty()){
                    FirstName.setError("please Enter Name");
                } else if (LastName.getText().toString().isEmpty()) {
                    LastName.setError("please Enter Last Name");
                } else if (ID.getText().toString().isEmpty()) {
                    ID.setError("Please Enter ID");
                } else  {
                    String _FirstName = FirstName.getText().toString();
                    String _LastName = LastName.getText().toString();
                    String _ID = ID.getText().toString();
                    Intent intent = new Intent(task_tow.this, TabLaout.class);
                    intent.putExtra("FirstName", _FirstName);
                    intent.putExtra("LastName", _LastName);
                    intent.putExtra("ID", _ID);
                    startActivity(intent);                }
            }
        });

    }

    private void SetUpId() {
        FirstName = findViewById(R.id.t2FirstName);
        LastName = findViewById(R.id.t2LastName);
        ID = findViewById(R.id.t2UserID);
        Save = findViewById(R.id.t2save);
    }
}