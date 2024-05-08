package com.example.test.main.Task2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

public class SecondFragment extends Fragment {


    TextView LastName;
    public SecondFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        LastName =view.findViewById(R.id.SFLastName);
        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("LastName");
            LastName.setText(data);
            //    Toast.makeText(getContext(), "a"+data, Toast.LENGTH_SHORT).show();
            // Use the data as needed
        }
        return  view;
    }
}