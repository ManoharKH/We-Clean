package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class CitizenHomeActivity extends AppCompatActivity {

    Button raiseComplaint, mycomplaints;
    FirebaseAuth mAuth;
    MeowBottomNavigation bottomNavigation;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menuIcon;
    int fragmentID = 1;
    int fragmentID1 = 2;
    int fragmentID2 = 3;


//    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_home);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigation = findViewById(R.id.bottom_nav);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        menuIcon = findViewById(R.id.menu_icon);

//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


//
//       toolbar = findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();



//


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_email_24));

        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_camera));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_arrow_back_24));

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new CitizenHomeFragment();
                        break;

                    case 2:
                        fragment = new CitizenMycomplaintFragment();
                        break;

                    case 3:
                        fragment = new CitizenRaiseComplaintFragment();
                        break;

                }
                loadFragment(fragment);
            }
        });

        fragmentID = getIntent().getExtras().getInt("fragmentId");
        bottomNavigation.show(fragmentID, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if (item.getId() == 1) {
                    Toast.makeText(CitizenHomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (item.getId() == 2) {
                    Toast.makeText(CitizenHomeActivity.this, "MyComplaints", Toast.LENGTH_SHORT).show();
                } else if (item.getId() == 3) {
                    Toast.makeText(CitizenHomeActivity.this, "RaiseComplaint", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

//        raiseComplaint = (Button) findViewById(R.id.btn_raisecomplaint);
//        mycomplaints = (Button) findViewById(R.id.btn_mycomplaints);
        mAuth = FirebaseAuth.getInstance();

//        raiseComplaint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(CitizenHomeActivity.this,RaisecomplaintActivity.class);
//                startActivity(it);
//            }
//        });
//
//        mycomplaints.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(CitizenHomeActivity.this,MyComplaintActivity.class);
//                startActivity(it);
//            }
//        });



        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment =new CitizenHomeFragment();
                        loadFragment(fragment);
                        //Intent it_home = new Intent(getApplicationContext(),CitizenHomeActivity.class);
                        //it_home.putExtra("fragmentId",fragmentID);
                        //startActivity(it_home);
                        break;

                    case R.id.nav_my_cmp:
                        //fragment = new CitizenMycomplaintFragment();
                        //loadFragment(fragment);
                        Intent it_my_cmp = new Intent(getApplicationContext(),CitizenHomeActivity.class);
                        it_my_cmp.putExtra("fragmentId",fragmentID1)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(it_my_cmp);
                        break;

                    case R.id.nav_raise_cmp:
                        //fragment = new CitizenRaiseComplaintFragment();
                        //loadFragment(fragment);
                        Intent it_raise_cmp = new Intent(getApplicationContext(),CitizenHomeActivity.class);
                        it_raise_cmp.putExtra("fragmentId",fragmentID2)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(it_raise_cmp);
                        break;

                    case R.id.nav_reportbug:
                        Intent sendmail = new Intent(Intent.ACTION_SEND).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        sendmail.setType("text/html");
                        sendmail.setPackage("com.google.android.gm"); //Added gmail package to forcefully open gmail app
                        sendmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"weclean@gmail.com"});
                        sendmail.putExtra(Intent.EXTRA_SUBJECT, "Your complaint subject");
                        sendmail.putExtra(Intent.EXTRA_TEXT,"Your complaint body");
                        startActivity(sendmail);
                        break;


                    case R.id.nav_profile:
                        Toast.makeText(CitizenHomeActivity.this, "User Details", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(CitizenHomeActivity.this, ProfileActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(it);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_logout:
                        Toast.makeText(CitizenHomeActivity.this, "User logged out", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Intent i = new Intent(CitizenHomeActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                        return true;


                    case R.id.nav_share:
                        Intent shareintent = new Intent(Intent.ACTION_SEND).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        shareintent.setType("text/plain");
                        shareintent.putExtra(Intent.EXTRA_SUBJECT, "WeClean App");
                        shareintent.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/drive/folders/1md3HcgJRw5Qfz5D0QN-xvr6gh7VHnQMl");
                        startActivity(Intent.createChooser(shareintent,"Share using"));
                        break;

                    default:
                        return true;


                }

                item.setChecked(true);
                drawerLayout.closeDrawers();


                return true;
            }
        });






    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_framelayout, fragment)
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}