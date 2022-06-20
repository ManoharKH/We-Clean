package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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

    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo dataConnection =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConnection != null && wifiConnection.isConnected()) ||
                (dataConnection != null && dataConnection.isConnected())){
            return true;
        }else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setMessage("Please connect to Internet.").setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                SplashActivity.this.finish();
            }
        }).show();
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

        FirebaseUser user = mAuth.getCurrentUser();
        if (isConnected()){
            // Internet is available
            if (user != null){
                String uid = user.getUid();
                checkUserCategory(uid);
            }else{
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }, 5000);
            }
        }else{
            // Ask to turn on Internet
            Toast.makeText(SplashActivity.this, "No internet", Toast.LENGTH_SHORT).show();
            showCustomDialog();
        }
    }
}