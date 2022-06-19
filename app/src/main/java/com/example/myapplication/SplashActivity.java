package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    LottieAnimationView lottieAnimationView1, lottieAnimationView2;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        lottieAnimationView1 = findViewById(R.id.lottie);
        lottieAnimationView2 = findViewById(R.id.textsl);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            String uid = user.getUid();
            checkUserCategory(uid);
        }else{
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 5000);
        }

    }

    private void checkUserCategory(String uid) {

        firebaseDatabase.getReference().child("Users").child(uid).child("category")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String usertype = snapshot.getValue(String.class);
                        if(usertype.equals("Citizen")){
                            Toast.makeText(SplashActivity.this,"Citizen",Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(SplashActivity.this,CitizenHomeActivity.class);
                            it.putExtra("fragmentId",1);
                            startActivity(it);
                            finish();

                        }else if(usertype.equals("Admin")){
                            Toast.makeText(SplashActivity.this,"Admin",Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(SplashActivity.this, AdminHomeActivity.class);
                            it.putExtra("fragmentId",1);
                            startActivity(it);
                            finish();

                        }else if(usertype.equals("Driver")){
                            Intent it = new Intent(SplashActivity.this, DriverHomeActivity.class);
                            startActivity(it);
                            Toast.makeText(SplashActivity.this,"Driver",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}