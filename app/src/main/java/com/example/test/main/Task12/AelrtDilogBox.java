package com.example.test.main.Task12;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class AelrtDilogBox extends AppCompatActivity {

    MaterialButton Btn1,Btn2,Btn3,Btn4,Btn5,Btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aelrt_dilog_box);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Btn1 = findViewById(R.id.t12Btn1);
        Btn1.setOnClickListener(v -> {
            BasicAlert();
        });
        Btn2 = findViewById(R.id.t12Btn2);
        Btn2.setOnClickListener(v -> {MultipleButtonAlert();});
        
        Btn3 = findViewById(R.id.t12Btn3);
        Btn3.setOnClickListener(v -> {ListButtonAlert();});


        Btn4 = findViewById(R.id.t12Btn4);
        Btn4.setOnClickListener(v -> {SingleButtonAlert();});


        Btn5 = findViewById(R.id.t12Btn5);
        Btn5.setOnClickListener(v -> {
            MultiselectButtonAlert();});

        Btn6 = findViewById(R.id.t12Btn6);
        Btn6.setOnClickListener(v -> {
            CustomButtonAlert();});


    }

    private void CustomButtonAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_user, null);
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        TextInputEditText FirstName = dialogView.findViewById(R.id.edit_text_first_name);
                        TextInputEditText LastName = dialogView.findViewById(R.id.edit_text_last_name);
                        TextInputEditText Email = dialogView.findViewById(R.id.edit_text_email);

                        Toast.makeText(this, "Name : "+FirstName.getText().toString()+" " + LastName.getText().toString()
                                +"\n Email : "+Email.getText().toString(), Toast.LENGTH_SHORT).show();

                    }catch (Exception e){

                    }

                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(this, "Cancel successfully ", Toast.LENGTH_SHORT).show();});
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void MultiselectButtonAlert() {

        String[] items = {"cat", "Dog", "Duck"};
        boolean[] checkedItems = {false, false, false};
        new AlertDialog.Builder(this)
                .setTitle("Choose items")
                .setMultiChoiceItems(items, checkedItems, (dialog, which, isChecked) -> {
                    Toast.makeText(this, "selected  element,  "+ items[which], Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(this, "Data Added  successfully", Toast.LENGTH_SHORT).show();
                })
                .show();

    }

    private void SingleButtonAlert() {
        String[] items = {"cat", "Dog", "Duck", "Bird"};
        new AlertDialog.Builder(this)
                .setTitle("Choose an item")
                .setSingleChoiceItems(items, -1, (dialog, which) -> {
                    Snackbar.make(findViewById(R.id.main), "selected  element,  "+items[which], Snackbar.LENGTH_SHORT).setAction("UNDO",v->{
                        CreateToast(items[which]);}).show();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(this, "Data Added  successfully", Toast.LENGTH_SHORT).show();
                })
                .show();

    }

    private void ListButtonAlert(  ) {
        String[] items = {"cat", "Dog", "Duck", "Bird"};
        new AlertDialog.Builder(this)
                .setTitle("Choose an item")
                .setItems(items, (dialog, which) -> {
                    Snackbar.make(findViewById(R.id.main), "selected  element,  "+items[which], Snackbar.LENGTH_SHORT).setAction("UNDO",v->{
                        CreateToast(items[which]);}).show();
                })
                .show();

    }

    private void CreateToast(String item) {
        Toast.makeText(this, "Unselected the element,"+ item, Toast.LENGTH_SHORT).show();

    }

    private void MultipleButtonAlert() {

        new AlertDialog.Builder(this)
                .setTitle("Warnings")
                .setMessage("This is Multiple Button Alert dialog Box")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Handle positive button click
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Handle negative button click
                })
                .show();

    }

    private void BasicAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Programs have an error, please contact to admin")
                .setPositiveButton("OK", null)
                .show();
    }
}