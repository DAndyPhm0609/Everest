package com.example.everest;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserInfo extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    FirebaseUser mUser;
    TextView name,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_user_info);
        name = (TextView) findViewById(R.id.nameField);
        address = (TextView) findViewById(R.id.addressField);
        phone = (TextView) findViewById(R.id.phoneView);

        fStore.collection("users").whereEqualTo("users", mAuth.getCurrentUser())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            getACollectionRef();
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                    }
                });
    }
    public CollectionReference getACollectionRef() {
        // [START fs_collection_ref]
        // Reference to the collection "users"
        CollectionReference collection = fStore.collection("users");
        // [END fs_collection_ref]
        return collection;
    }
}