package com.mobileassignment3.parcel_tracking_app;

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
import com.mobileassignment3.parcel_tracking_app.model_classes.DeliveryJob;
import com.mobileassignment3.parcel_tracking_app.model_classes.Parcel;
import com.mobileassignment3.parcel_tracking_app.model_classes.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class FirebaseController {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Initialize Firebase Auth
    public FirebaseController() {
        mAuth = FirebaseAuth.getInstance();
    }

    //Firebase setter method
    public void writeMasterDeliveryJobsToFirestore(){
        ArrayList<DeliveryJob> deliveryJobArrayList = new ArrayList<DeliveryJob>();

        String[] senders = {"Danica", "Lakhsay", "John Casey", "Raza", "Obama", "Paul Bartlett", "Dila"};
        String[] packages = {"Letter", "Laptop", "Jacket", "Certificate", "Backpack", "Payslip", "Vaccine" };

        //Writing 7 random delivery jobs to a temp delivery job array list
        for(int i=0;i<7;i++) {
            DeliveryJob nDJ = new DeliveryJob();
            Random rand1 = new Random();
            Random rand2 = new Random();

            int n = rand1.nextInt(7);
            int m = rand2.nextInt(7);

            nDJ.addParcel(new Parcel( packages[n] + " from " + senders[m]));
            deliveryJobArrayList.add(nDJ);
        }

        Map<String, Object> masterDeliveryJobs = new HashMap<>();
        //Putting the delivery job array list into a hashmap
        masterDeliveryJobs.put("masterList", deliveryJobArrayList);


        //Get the delivery jobs document which contains all delivery items
        DocumentReference deliveryJobsDocumentRef = db.collection("masterDeliveryJobs").document("deliveryJobsDocument");
        //Add the newly created delivery jobs to the masterList
        deliveryJobsDocumentRef
                .update("masterList", deliveryJobArrayList)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FIREBASE", "Data successfully added!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIREBASE", "Error updating document", e);
                    }
                });
    }

//// TODO make this function only read a single document from the master deliveryjobs collection
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

    public FirebaseUser createNewUser(String email, String password) {
        FirebaseUser user = getCurrentUser();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                           Log.d("ERROR","firebase error can not make new user");
                        }

                        // ...
                    }
                });
        return user;
    }

}
