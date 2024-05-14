package com.example.test.main.task10;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class Task10 extends AppCompatActivity {


    MaterialCheckBox c1,c2,c3;
    MaterialRadioButton r1,r2;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task10);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpIds();
        CheckBox();
        RadioButton();
        RadioGroupSet();






    }

    private void RadioGroupSet() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                MaterialRadioButton button =findViewById(checkedId);
                if(button!= null){
                    Toast.makeText(Task10.this, button.getText().toString() +"Selected", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Task10.this, button.getText().toString() +"Deselected", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void RadioButton() {




        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ){
                    rg.setVisibility(View.VISIBLE);
                    Toast.makeText(Task10.this, r1.getText()+"Selected", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(Task10.this, r1.getText()+"Deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rg.setVisibility(View.VISIBLE);
                    Toast.makeText(Task10.this, r2.getText()+"Selected", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Task10.this, r2.getText()+"Deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CheckBox() {
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(Task10.this, c1.getText()+"Selected", Toast.LENGTH_SHORT).show();

                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(Task10.this, c1.getText()+"Deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    Toast.makeText(Task10.this, c2.getText()+"Selected", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Task10.this, c2.getText()+"Deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    Toast.makeText(Task10.this, c3.getText()+"Selected", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Task10.this, c3.getText()+"Deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpIds() {
        c1 = findViewById(R.id.t10Fruits);
        c2 = findViewById(R.id.t10Flower);
        c3 = findViewById(R.id.t10Animal);
        r1 = findViewById(R.id.t10Male);
        r2 = findViewById(R.id.t10femele);
        rg = findViewById(R.id.t10RadioGroup);
    }
}