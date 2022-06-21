package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import soup.neumorphism.NeumorphCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private NeumorphCardView pendC, addDriver, inprog, workC, resolvedC, cardRight;

   
    private TextView wc, pt, pt2, at, at2, it, it2, wt,wt2, rt, rt2;
    private ShapeableImageView pi, ai, wi, ii, ri;
    int fragmentId = 1;
    private float v = 0;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminHomeFragment newInstance(String param1, String param2) {
        AdminHomeFragment fragment = new AdminHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        pendC = view.findViewById(R.id.pend_comp);
        addDriver = view.findViewById(R.id.adddriver);
        inprog = view.findViewById(R.id.in_prog);
        workC =view.findViewById(R.id.work_comp);
        resolvedC = view.findViewById(R.id.resolved_comp);
        cardRight = view.findViewById(R.id.cardright);
        wc = view.findViewById(R.id.txtweclean);
        pi = view.findViewById(R.id.pi);
        pt = view.findViewById(R.id.pt);
        pt2 =view.findViewById(R.id.pt2);
        ai = view.findViewById(R.id.ai);
        at = view.findViewById(R.id.at);
        at2 = view.findViewById(R.id.at2);
        ii =view.findViewById(R.id.ii);
        it = view.findViewById(R.id.it);
        it2 = view.findViewById(R.id.it2);
        ri = view.findViewById(R.id.ri);
        rt =view.findViewById(R.id.rt);
        rt2 =view.findViewById(R.id.rt2);
        wi = view.findViewById(R.id.wi);
        wt =view.findViewById(R.id.wt);
        wt2 =view.findViewById(R.id.wt2);


        cardRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PiechartActivity.class);
                startActivity(i);
            }
        });

        wc.setTranslationY(300);
        pendC.setTranslationY(300);
        pi.setTranslationY(300);
        pt.setTranslationY(300);
        pt2.setTranslationY(300);
        addDriver.setTranslationY(300);
        ai.setTranslationY(300);
        at.setTranslationY(300);
        at2.setTranslationY(300);
        inprog.setTranslationY(300);
        ii.setTranslationY(300);
        it.setTranslationY(300);
        it2.setTranslationY(300);
        workC.setTranslationY(300);
        wi.setTranslationY(300);
        wt.setTranslationY(300);
        wt2.setTranslationY(300);
        resolvedC.setTranslationY(300);
        ri.setTranslationY(300);
        rt.setTranslationY(300);
        rt2.setTranslationY(300);


        wc.setAlpha(v);
        pendC.setAlpha(v);
        pi.setAlpha(v);
        pt.setAlpha(v);
        pt2.setAlpha(v);
        addDriver.setAlpha(v);
        ai.setAlpha(v);
        at.setAlpha(v);
        at2.setAlpha(v);
        inprog.setAlpha(v);
        ii.setAlpha(v);
        it.setAlpha(v);
        it2.setAlpha(v);
        workC.setAlpha(v);
        wi.setAlpha(v);
        wt.setAlpha(v);
        wt2.setAlpha(v);
        resolvedC.setAlpha(v);
        ri.setAlpha(v);
        rt.setAlpha(v);
        rt2.setAlpha(v);



        wc.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        pendC.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        pi.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        pt.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        pt2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        addDriver.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        ai.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        at.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        at2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        inprog.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        ii.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        it.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        it2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        workC.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        wi.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        wt.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        wt2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        resolvedC.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        ri.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        rt.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        rt2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();


        pendC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), AdminHomeActivity.class);
                it.putExtra("fragmentId",2);
                startActivity(it);
            }
        });

        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), AdminHomeActivity.class);
                it.putExtra("fragmentId",3);
                startActivity(it);
            }
        });

        inprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), AdminHomeActivity.class);
                it.putExtra("fragmentId",4);
                startActivity(it);
            }
        });

        workC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), AdminHomeActivity.class);
                it.putExtra("fragmentId",5);
                startActivity(it);
            }
        });

        resolvedC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), AdminHomeActivity.class);
                it.putExtra("fragmentId",6);
                startActivity(it);
            }
        });


        return view;
    }
}
