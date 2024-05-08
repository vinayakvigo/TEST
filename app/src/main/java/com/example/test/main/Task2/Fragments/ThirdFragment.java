package com.example.test.main.Task2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

public class ThirdFragment extends Fragment {


    TextView ID;
    public ThirdFragment() {
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
        View view =inflater.inflate(R.layout.fragment_third, container, false);
        ID = view.findViewById(R.id.TFID);
        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("ID");
            ID.setText(data);
            //    Toast.makeText(getContext(), "a"+data, Toast.LENGTH_SHORT).show();
            // Use the data as needed
        }
        return view;
    }
}