package com.mobileassignment3.parcel_tracking_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
    public FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Initialize Firebase Auth


    public FirebaseController() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void handleGoogleSignIn(GoogleSignInAccount account,Activity activity) {
try{

    firebaseAuthWithGoogle(account.getIdToken());
    FirebaseUser cu = mAuth.getCurrentUser();

    if(cu!=null){
        Toast.makeText(activity, "Welcome!"+ cu.getDisplayName(), Toast.LENGTH_SHORT).show();
    }
    updateUIafterLogin(activity,true);

}catch (Exception e){
    Toast.makeText(activity, account.getDisplayName(), Toast.LENGTH_SHORT).show();
    Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
}




    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                           // Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
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


    public FirebaseUser createNewUser(final Activity activity, String email, String password, final int type, final String username) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(activity, "SUCCESS! you can log in now", Toast.LENGTH_LONG).show();
                            setupUserInDatabase(username,user,type);
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
        return getCurrentUser();
    }

    private void setupUserInDatabase(String username,FirebaseUser user, int type) {
        User parcelAppUser = new User();
        parcelAppUser.setType(type);
        parcelAppUser.setEmail(getCurrentUser().getEmail());
        parcelAppUser.setUsername(username);

        db.collection("users").document(getCurrentUser().getUid()).set(parcelAppUser);
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

    private void updateUIafterLogin(final Activity activity, boolean loginSuccess) {
        FirebaseUser cu = getCurrentUser();

        DocumentReference docRef = db.collection("users").document(cu.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);

                doIntent(user,activity);

            }
        });


    }
    void doIntent(User user ,Activity activity) {
        Intent myIntent =new Intent(activity, LoginActivity.class);
        if (user.getPrimaryType()==User.DRIVER){
             myIntent = new Intent(activity, DriverMainActivity.class);


        }else if (user.getPrimaryType()==User.RECIEVER){
            myIntent = new Intent(activity, ReceiverMainActivity.class);
        }else {
             myIntent = new Intent(activity, AdminMainActivity.class);
        }

        //
        //
        activity.startActivity(myIntent);

    }
    public void logoutCurrentUser() {
        FirebaseAuth.getInstance().signOut();
    }
}
