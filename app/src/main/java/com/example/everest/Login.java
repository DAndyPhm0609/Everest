package com.example.everest;

import static com.example.everest.HomePage.BookArrayList;
import static com.example.everest.HomePage.recyclerList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Login extends AppCompatActivity {
    Button login;
    EditText email, password;
    TextView emailV, passV;
    FirebaseAuth mAuth;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        login = (Button) findViewById(R.id.loginId);
        email = (EditText) findViewById(R.id.EmailText);
        password = (EditText) findViewById(R.id.regPassText);
        emailV = (TextView) findViewById(R.id.regEmailId);
        passV = (TextView) findViewById(R.id.regPassId);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    //login function
    private void loginUser() {
        String emailLogin = email.getText().toString();
        String passwordLogin = password.getText().toString();

        //check if email & password valid according to firestore
        if (TextUtils.isEmpty(emailLogin)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(passwordLogin)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //get database of user when sign in successful
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        fireStore.collection("users").get().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task1.getResult()) {
                                    User user = documentSnapshot.toObject(User.class);

                                    //store database into local variable to display in homepage and user info
                                    String email = user.getEmail();
                                    if (emailLogin.equals(email)) {
                                        userName = user.getName();

                                        Log.d("TAG", "USERNAME HERE, TRASH: " + userName);

                                        //send data to homepage and user info
                                        Intent i = new Intent(Login.this, HomePage.class);
                                        i.putExtra("name", userName);
                                        i.putExtra("address", user.address);
                                        i.putExtra("phone", user.phone);
                                        startActivity(i);
                                        break;
                                    }
                                }
                            }
                        });
                        //create horizontal recycler view
                        createBookList();
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //go back button if user have not registered
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(Login.this, MainPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //get book database from fire store and create recycler view, list view in homepage
    public void createBookList() {
        //create vertical list view
        fireStore.collection("books")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            Book book = documentSnapshot.toObject(Book.class);

                            String name = book.getName();
                            String author = book.getAuthor();
                            String price = book.getPrice();
                            String des = book.getDes();
                            Double rating = book.getRating();
                            String imgURL = book.getUrl();
                            BookArrayList.add(new Book(name, author, price, rating, des, imgURL));
                        }
                    }
                });

        //create horizontal recycler view
        fireStore.collection("books").whereEqualTo("rating", 5)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            Book book = documentSnapshot.toObject(Book.class);

                            String name = book.getName();
                            String author = book.getAuthor();
                            String price = book.getPrice();
                            String des = book.getDes();
                            Double rating = book.getRating();
                            String imgURL = book.getUrl();
                            recyclerList.add(new Book(name, author, price, rating, des, imgURL));
                        }
                    }
                });
    }
}

