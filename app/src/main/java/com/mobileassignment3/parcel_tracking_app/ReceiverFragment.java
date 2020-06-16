package com.mobileassignment3.parcel_tracking_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceiverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiverFragment extends Fragment {
    public ReceiverFragment() {
        // Required empty public constructor
    }

    public static ReceiverFragment newInstance() {
        ReceiverFragment fragment = new ReceiverFragment();
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
        return inflater.inflate(R.layout.fragment_receiver, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState){
        Button btnSignupReceiver = view.findViewById(R.id.btnSignupReceiver);
        btnSignupReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ReceiverFragment.this.getContext(), LoginActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
