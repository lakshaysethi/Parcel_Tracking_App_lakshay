package com.mobileassignment3.parcel_tracking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class SignupActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //will hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //hide the title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        // Setup pager
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        Button signupBtn = findViewById(R.id.btnSignup);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  new FirebaseController().createNewUser();
            }
        });


    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link DriverFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class DriverFragment extends Fragment {
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
    //        Button btnSignupDriver = view.findViewById(R.id.btnSignupDriver);
    //        btnSignupDriver.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                Intent myIntent = new Intent(DriverFragment.this.getContext(), LoginActivity.class);
    //                startActivity(myIntent);
    //            }
    //        });

        }
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link ReceiverFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class ReceiverFragment extends Fragment {
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
    //        Button btnSignupReceiver = view.findViewById(R.id.btnSignupReceiver);
    //        btnSignupReceiver.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                Intent myIntent = new Intent(ReceiverFragment.this.getContext(), LoginActivity.class);
    //                startActivity(myIntent);
    //            }
    //        });

        }
    }
}




//TODO fix depreciated code pls :)
class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);// TODO here
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Driver";
        } else {
            return "Receiver";
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SignupActivity.DriverFragment.newInstance();
        } else {
            return SignupActivity.ReceiverFragment.newInstance();
        }
    }
}