package com.mobileassignment3.parcel_tracking_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobileassignment3.parcel_tracking_app.activities.auth_activities.LoginActivity;
import com.mobileassignment3.parcel_tracking_app.activities.main_activities.AdminMainActivity;
import com.mobileassignment3.parcel_tracking_app.activities.main_activities.DriverMainActivity;
import com.mobileassignment3.parcel_tracking_app.activities.main_activities.ReceiverMainActivity;
import com.mobileassignment3.parcel_tracking_app.model_classes.DeliveryJob;
import com.mobileassignment3.parcel_tracking_app.model_classes.Parcel;
import com.mobileassignment3.parcel_tracking_app.model_classes.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class FirebaseController {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Initialize Firebase Auth


    public FirebaseController() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void writeMasterDeliveryJobsToFirestore(){
        ArrayList<DeliveryJob> djAl = new ArrayList<DeliveryJob>();

        for(int i=0;i<10;i++) {
            DeliveryJob nDJ = new DeliveryJob();
            nDJ.addParcel(new Parcel("Gift From Auntie Cinda :"+1));

            djAl.add(nDJ);
        }

        Map<String, Object> masterDeliveryJobs = new HashMap<>();

        masterDeliveryJobs.put("masterList", djAl);
//TODO fix the following to not add but it should update existing list
        // Add a new document with a generated ID
        db.collection("masterDeliveryJobs")
                .add(masterDeliveryJobs)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("FIREBASE", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIREBASE error", "Error adding document", e);
                    }
                });




    }
// TODO make this function only read a single document from the master deliveryjobs collection
    public ArrayList<DeliveryJob> getdeliveryJobsAssociatedWithAuthenticatedUser() {
        FirebaseUser currentuser = getCurrentUser();
        ArrayList<DeliveryJob> djAl = new ArrayList<DeliveryJob>();

        db.collection("masterDeliveryJobs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("FIREBASE", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Firebase error", "Error getting documents.", task.getException());
                        }
                    }
                });

        return djAl;
    }

    //void logFirestoreData() {}



//TODO change return type to Our Model classes instead of FirebaseUser
    public FirebaseUser getCurrentUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        return currentUser;
    }


    public FirebaseUser createNewUser(final Activity activity,String email,String password) {
        FirebaseUser user = getCurrentUser();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(activity, "SUCCESS! you can log in now", Toast.LENGTH_LONG).show();

                            Intent gotoLoginScreen = new Intent(activity, LoginActivity.class);
                            activity.startActivity(gotoLoginScreen);

                        } else {
                            // If sign in fails, display a message to the user.
                           Log.d("ERROR","firebase error can not make new user");
                           if(task.getException().toString().contains("already"))
                               Toast.makeText(activity, "That Email is already in use please try another email", Toast.LENGTH_LONG).show();
                           else Toast.makeText(activity, "Could not sign you up :  "+task.getException(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
        return user;
    }

    public void loginUser(final Activity activity , String email, String password) {
        logoutCurrentUser();
        if (email !=null&&!email.equals("")){
            if(password!=null&&!password.equals("")){

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null)
                                        Toast.makeText(activity.getApplicationContext(),
                                                "Welcome! "+ user.getEmail(), Toast.LENGTH_LONG).show();
                                    else Toast.makeText(activity.getApplicationContext(),
                                            "Failed - user is null", Toast.LENGTH_LONG).show();
                                    updateUIafterLogin(activity,true);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    if (task.getException().getClass().toString().contains("Credentials"))
                                    Toast.makeText(activity.getApplicationContext(), "Failed to Login: Invalid Credentials", Toast.LENGTH_LONG).show();
                                    else if (task.getException().getClass().toString().contains("TooManyRequest"))
                                        Toast.makeText(activity.getApplicationContext(),"Too many Requests please wait a few seconds before trying again" , Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(activity.getApplicationContext(),task.getException().getClass().toString() , Toast.LENGTH_LONG).show();



                                }
                            }
                        });
            }else{
                Toast.makeText(activity, "Please enter your PASSWORD", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(activity, "Please Enter your EMAIL", Toast.LENGTH_LONG).show();
        }
    }

    private void updateUIafterLogin(Activity activity,boolean success) {

    //  Intent myIntent = new Intent(activity, AdminMainActivity.class);
        Intent myIntent = new Intent(activity, DriverMainActivity.class);
    //  Intent myIntent = new Intent(activity, ReceiverMainActivity.class);
        activity.startActivity(myIntent);
    }

    private void logoutCurrentUser() {
        FirebaseAuth.getInstance().signOut();
    }
}
