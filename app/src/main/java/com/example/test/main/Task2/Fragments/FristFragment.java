package com.example.test.main.Task2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;

public class FristFragment extends Fragment {

    TextView FristName;
    public FristFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_frist, container, false);
      FristName =view.findViewById(R.id.FFFirstName);
        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("FirstName");
            FristName.setText(data);
        //    Toast.makeText(getContext(), "a"+data, Toast.LENGTH_SHORT).show();
            // Use the data as needed
        }


        return view;
    }
}