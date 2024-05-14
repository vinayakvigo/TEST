package com.example.test.main.Task8;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.main.MainActivity;
import com.example.test.main.UserAdapter;
import com.example.test.main.model.UserModel;
import com.example.test.main.userList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RealTimeUsers extends AppCompatActivity {

    RecyclerView recyclerView;
   RealTimeAdapter adapter;
   List<UserModel> Lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_real_time_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       // Lists.add(new UserModel("0","adfasdf","adfasdfa","adfadsf"));
        recyclerView = findViewById(R.id.t8_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new RealTimeAdapter(Lists, RealTimeUsers.this);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Lists.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.i("my", "onDataChange: "+dataSnapshot.getValue());

                    String name = dataSnapshot.child("name").getValue(String.class);
                    String emailId = dataSnapshot.child("emailId").getValue(String.class);
                    String Password = dataSnapshot.child("Password").getValue(String.class);
                    String UserUuid = dataSnapshot.child("UserUuid").getValue(String.class);
                    UserModel user = new UserModel(name,emailId,Password,UserUuid);
                    Log.i("my2", "onDataChange: "+dataSnapshot.child("name").getValue(String.class));

                     Lists.add(user);
                }
                Log.i("my", "onDataChange: "+Lists.toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RealTimeUsers.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}