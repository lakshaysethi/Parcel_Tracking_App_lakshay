package com.mobileassignment3.parcel_tracking_app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobileassignment3.parcel_tracking_app.model_classes.DeliveryJob;
import com.mobileassignment3.parcel_tracking_app.model_classes.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseController {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
// Initialize Firebase Auth


    public FirebaseController() {
        mAuth = FirebaseAuth.getInstance();
    }

    void writeMasterDeliveryJobsToFirestore(){
        ArrayList<DeliveryJob> djAl = new ArrayList<DeliveryJob>();

        for(int i=0;i<10;i++) {
            DeliveryJob nDJ = new DeliveryJob();
            nDJ.addParcel(new Parcel("Gift From Auntie Cinda :"+1));

            djAl.add(nDJ);
        }

        Map<String, Object> masterDeliveryJobs = new HashMap<>();

        masterDeliveryJobs.put("masterList", djAl);

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

    ArrayList<DeliveryJob> getdeliveryJobsAssociatedWithAuthenticatedUser() {

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




    public FirebaseUser getCurrentUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser;
    }
}
