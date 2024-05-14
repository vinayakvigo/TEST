package com.example.test.main.Task8;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.example.test.main.Other.OtherFunction;
import com.example.test.main.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RealtimeDatabase extends AppCompatActivity {

    TextInputEditText Name,EmailId,Pass;
    MaterialButton AddUserBtn,ShowUserBtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_realtime_database2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SetUpId();
        AddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser();
            }
        });
        ShowUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RealtimeDatabase.this, RealTimeUsers.class));
            }
        });
    }
    private void SetUpId() {

        Name = findViewById(R.id.t8Name);
        EmailId = findViewById(R.id.t8Email);
        AddUserBtn = findViewById(R.id.t8AddUser);
        ShowUserBtn = findViewById(R.id.t8SaveUser);
        Pass = findViewById(R.id.t8Password);
    }
    private void AddUser() {
        progressDialog = new ProgressDialog(RealtimeDatabase.this);
        progressDialog.setMessage("Adding User...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String _Name = Name.getText().toString();
        String _EmailId =EmailId.getText().toString();
        String _Pass =Pass.getText().toString();
        if(_Name.isEmpty()){
            progressDialog.dismiss();
            Name.setError("Please enter the valid name");
        } else if(_Pass.isEmpty()){
            progressDialog.dismiss();
            Pass.setError("Please enter the valid Pass");
        }else if(_EmailId.isEmpty()){
            progressDialog.dismiss();
            EmailId.setError("Please enter the valid Email");
        } else if(!OtherFunction.isValidEmail(_EmailId)){
            progressDialog.dismiss();
            EmailId.setError("Please enter the valid Email");
        }else{
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(_EmailId,_Pass).addOnCompleteListener( RealtimeDatabase.this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RealtimeDatabase.this, "User added", Toast.LENGTH_SHORT).show();
                        try{
                            String Uuid = task.getResult().getUser().getUid();
                            UserModel model = new UserModel(_Name,_EmailId,_Pass,Uuid);
                            DatabaseReference mDatabase;
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            mDatabase.child("Users").child(Uuid).setValue(model);
                            progressDialog.dismiss();
                        }catch (Error e){
                            Log.i("my", "error a",e);

                        }
                    }
                    else {
                        Toast.makeText(RealtimeDatabase.this, "Authentication failed." + task.getException(),
                                Toast.LENGTH_LONG).show();

                        progressDialog.dismiss();

                    }
                }
            });
        }

    }
}