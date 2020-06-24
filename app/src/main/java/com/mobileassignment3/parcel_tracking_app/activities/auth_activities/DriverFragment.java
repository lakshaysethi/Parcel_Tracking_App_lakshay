package com.mobileassignment3.parcel_tracking_app.activities.auth_activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobileassignment3.parcel_tracking_app.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DriverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriverFragment extends Fragment {
    public DriverFragment() {
        // Required empty public constructor
    }

    public static DriverFragment newInstance() {
        DriverFragment fragment = new DriverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState){
        Button btnSignupDriver = view.findViewById(R.id.btnSignupDriver);
        btnSignupDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DriverFragment.this.getContext(), LoginActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
