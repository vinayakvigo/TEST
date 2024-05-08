package com.example.test.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.R;
import com.example.test.main.SQL.UserOpration;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public  Button AddUserbtn;
    public  Button ShowUserbtn;
    UserOpration op = new UserOpration(MainActivity.this);

    MaterialTextView FristName,LastName,EmailId;
    TextInputEditText UserId;
    String id;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SetUpIDs();
        int data = op.getUserCount();
        AddUserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id= UserId.getText().toString();
                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this, "ps" + id, Toast.LENGTH_SHORT).show();
                    UserId.setError("invalid id");
                }
                else if(op.isUserIdExists(Integer.parseInt(id))){
                    //Toast.makeText(MainActivity.this, "true" + id, Toast.LENGTH_SHORT).show();
                    UserId.setError("this User already exist");
                }  else {
                     fetchDataFromRandomUserAPI();
                }
               // fetchDataFromRandomUserAPI();
            }
        });

        ShowUserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, userList.class);
                startActivity(intent);
            }
        });
    }
    private void fetchDataFromRandomUserAPI() {
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "https://randomuser.me/api/", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            showProgressDialog();


                            JSONObject userObject = response.getJSONArray("results").getJSONObject(0);
                            String firstName = userObject.getJSONObject("name").getString("first");
                            String lastName = userObject.getJSONObject("name").getString("last");
                            String email = userObject.getString("email");

                            String message = "Name: " + firstName + " " + lastName + "\nEmail: " + email;
                            Log.i("TAG", "onResponse: "+message);
                            op.insertUser(firstName,lastName,email,Integer.parseInt(id));
                            FristName.setVisibility(View.VISIBLE);
                            LastName.setVisibility(View.VISIBLE);
                            EmailId.setVisibility(View.VISIBLE);
                            FristName.setText(firstName);
                            LastName.setText(lastName);
                            EmailId.setText(email);
                            showToast(message);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            hideProgressDialog();

                            progressBar.setVisibility(View.GONE);
                            UserId.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            showToast("Error parsing JSON");
                            Log.i("TAG2", "onResponse: "+e.toString());

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast("Error fetching user data");
                        Log.i("TAG2", "onResponse: "+error.toString());

                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    private void AddUser() {

        Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
    }
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false); // Set this to true if you want the user to be able to cancel the operation
        progressDialog.show();
    }
    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    public void SetUpIDs() {
        AddUserbtn = findViewById(R.id.AddUser);
        ShowUserbtn = findViewById(R.id.ShowUser);
        UserId = findViewById(R.id.UserId);
        FristName = findViewById(R.id.FristName);
        LastName = findViewById(R.id.LastName);
        EmailId = findViewById(R.id.EmailId);
        progressBar = findViewById(R.id.progress_bar);



    }
}