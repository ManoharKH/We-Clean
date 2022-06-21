package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitizenHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitizenHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private float v=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btnraiseComplaint, btnmyComplaint;
    TextView raisec, compraise, myc, compmy,txtusername;
    RelativeLayout yel, blck;
    ImageView yimg, bimg;
    int fragmentID = 3;
    int fragmentID1 = 2;


    public CitizenHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitizenHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CitizenHomeFragment newInstance(String param1, String param2) {
        CitizenHomeFragment fragment = new CitizenHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_citizen_home, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        btnraiseComplaint = view.findViewById(R.id.raisecomplaint);
        btnmyComplaint = view.findViewById(R.id.mycomplaints);
        raisec = view.findViewById(R.id.raisew);
        compraise = view.findViewById(R.id.compw);
        myc = view.findViewById(R.id.myw);
        compmy = view.findViewById(R.id.compww);
        txtusername = view.findViewById(R.id.txt_username);
        yel = view.findViewById(R.id.yclr);
        blck = view.findViewById(R.id.bclr);
        yimg = view.findViewById(R.id.rc_img);
        bimg = view.findViewById(R.id.mc_img);

        btnraiseComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),CitizenHomeActivity.class);
                it.putExtra("fragmentId",fragmentID);
                startActivity(it);
            }
        });

        btnmyComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),CitizenHomeActivity.class);
                it.putExtra("fragmentId",fragmentID1);
                startActivity(it);
            }
        });

        getUsername();

        yel.setTranslationX(800);
        blck.setTranslationX(800);
        raisec.setTranslationX(800);
        compraise.setTranslationX(800);
        btnraiseComplaint.setTranslationX(800);
        myc.setTranslationX(800);
        compmy.setTranslationX(800);
        btnmyComplaint.setTranslationX(800);
        yimg.setTranslationX(800);
        bimg.setTranslationX(800);

        yel.setAlpha(v);
        blck.setAlpha(v);
        raisec.setAlpha(v);
        compraise.setAlpha(v);
        btnraiseComplaint.setAlpha(v);
        myc.setAlpha(v);
        compmy.setAlpha(v);
        btnmyComplaint.setAlpha(v);
        yimg.setAlpha(v);
        bimg.setAlpha(v);

        yel.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        raisec.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        compraise.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        btnraiseComplaint.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        yimg.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        blck.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        myc.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        compmy.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnmyComplaint.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        bimg.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();







        return view;


    }

    private void getUsername() {
        databaseReference = firebaseDatabase.getReference("Users");

        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot user : snapshot.getChildren()){
//                    String uid = user.getKey();
//                    if(uid.equals(currentUserID)){
//                        txtusername.setText(user.child("username").getValue(String.class));
//                    }
//                }

                DataSnapshot user = snapshot.child(currentUserID);
                txtusername.setText(user.child("username").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}