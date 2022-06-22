package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtusername,txtmob,txtcategory,txtnumberOfComaints,txttvnoOfComaplaints;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    int numberOfComplaints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtusername = (TextView) findViewById(R.id.txt_username);
        txtmob = (TextView) findViewById(R.id.txt_mobilenumber);
        txtcategory = (TextView) findViewById(R.id.txt_category);
        txtnumberOfComaints = (TextView) findViewById(R.id.txt_noofcomplaint);
        txttvnoOfComaplaints = (TextView) findViewById(R.id.txt_tvnoofcomplaint);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        getUserDetails();
        countUserComplaints();
    }

    private void getUserDetails() {
        String userID = mAuth.getCurrentUser().getUid();

        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // find user with userID
                DataSnapshot currentUser = snapshot.child(userID);

                String userCategory = currentUser.child("category").getValue(String.class);
                txtusername.setText(currentUser.child("username").getValue(String.class));
                txtmob.setText(currentUser.child("mobile").getValue(String.class));
                txtcategory.setText(userCategory);

                if (userCategory.equals("Citizen")){
                    txttvnoOfComaplaints.setVisibility(View.VISIBLE);
                    txtnumberOfComaints.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void countUserComplaints() {
        String userID = mAuth.getCurrentUser().getUid();

        databaseReference.child("Complaints").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot complaint : snapshot.getChildren()){
                    String complaintUserID = complaint.child("userID").getValue(String.class);
                    if (complaintUserID.equals(userID)){
                        numberOfComplaints += 1;
                    }
                }
                txtnumberOfComaints.setText("" + numberOfComplaints);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}