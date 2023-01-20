package com.example.everest;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;
import android.os.Bundle;
import android.view.View;

import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    public static ArrayList<Book> recyclerList = new ArrayList<>();
    public static List<Book> cartList = new ArrayList<>();
    public static ArrayList<Book> BookArrayList = new ArrayList<>();
    RecyclerView recyclerBook, listBook;
    private static final String TAG = "HomePage";
    public TextView welcomeText;
    static String userName, userAddress, userPhone;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String Uid;

    public BookDisplayAdapter bookAdapter;
    public ImageButton showCart;
    public ImageButton info;
    private SearchView searchView;

    ListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getUserInfo();
        //Text view to welcome the user
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        String name;

        //get intent from login to get user's name
        Intent i = getIntent();
        //get username from intent to display in welcome text
        name = (String) i.getStringExtra("name");
        System.out.println("From homepage" + name);
        welcomeText.setText(String.format("Hello %s", name));
        //button image to show the user's shopping cart
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        showCart = (ImageButton) findViewById(R.id.shoppingCart);
        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, ShowCart.class);
                startActivity(i);
            }
        });

        //Image button to access our user's profile
        info = (ImageButton) findViewById(R.id.cusInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, UserInfo.class);
                i.putExtra("name", getIntent().getStringExtra("name"));
                i.putExtra("address", getIntent().getStringExtra("address"));
                i.putExtra("phone", getIntent().getStringExtra("phone"));
                startActivityForResult(i, 300);
            }
        });
//                addButton = (Button) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(HomePage.this, AddBook.class);
//                startActivity(i);
//            }
//        });
        //call function to generate our adapter view
        generateRecyclerView();
        generateListView();
    }

    //function to use adapter to create our recycler view
    private void generateRecyclerView() {
        recyclerBook = findViewById(R.id.recyclerView);
        recyclerBook.setHasFixedSize(true);
        recyclerBook.setLayoutManager(new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookDisplayAdapter(getApplication(), recyclerList);
        recyclerBook.setAdapter(bookAdapter);
    }
    //function to create the list view below the recycler view
    private void generateListView() {
        listBook = findViewById(R.id.listView);
        listBook.setHasFixedSize(true);
        listBook.setLayoutManager(new LinearLayoutManager(HomePage.this));
        listAdapter = new ListViewAdapter(getApplication(), BookArrayList);
        listBook.setAdapter(listAdapter);
    }

    public void getUserInfo() {
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        Uid = mAuth.getCurrentUser().getUid();
        fStore.collection("users").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for(QueryDocumentSnapshot document:task.getResult()){
                            Log.d(TAG, document.getId() + "=>" + document.getData());
                            userName = getIntent().getStringExtra("name");
                            userAddress = getIntent().getStringExtra("address");
                            userPhone = getIntent().getStringExtra("phone");
                        }
                    }else{
                        Log.w(TAG,"Error getting User", task.getException());
                    }

                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 300) {
            if (resultCode == RESULT_OK) {
                recyclerList = new ArrayList<>();
                BookArrayList = new ArrayList<>();
                finish();
            }
        }
    }
}