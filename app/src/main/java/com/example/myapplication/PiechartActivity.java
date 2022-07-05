package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PiechartActivity extends AppCompatActivity {

    private PieChart pieChart;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private int noActionsTakenInt = 0;
    private int complaintVerifiedInt = 0;
    private int driverAddedInt = 0;
    private int cleaningDoneInt = 0;
    private int complaintResolvedInt = 0;
    private int totalInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        firebaseDatabase = FirebaseDatabase.getInstance();
        pieChart = findViewById(R.id.pie_chart);
        calculateDifferentTypeofComplaints();


      getSupportActionBar().hide();
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void calculateDifferentTypeofComplaints() {
        databaseReference = firebaseDatabase.getReference("Complaints");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot complaint : snapshot.getChildren()){
                    String adminStatus = complaint.child("adminStatus").getValue(String.class);
                    if (adminStatus.equals("No Actions Taken")){
                        noActionsTakenInt += 1;
                    }else if (adminStatus.equals("Complaint Verified")){
                        complaintVerifiedInt += 1;
                    }else if (adminStatus.equals("Driver Added")){
                        driverAddedInt += 1;
                    }else if (adminStatus.equals("Cleaning Done")){
                        cleaningDoneInt += 1;
                    }else if (adminStatus.equals("Complaint Resolved")){
                        complaintResolvedInt += 1;
                    }
                    totalInt += 1;
                }
                setupPieChart();
                loadPieChartData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Complaints Report");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {

        float pendingComplaintFloat = (float) noActionsTakenInt / (float) totalInt;
        float driverAddedFloat = (float) complaintVerifiedInt / (float) totalInt;
        float workInProgressFloat = (float) driverAddedInt / (float) totalInt;
        float workCompletedFloat = (float) cleaningDoneInt / (float) totalInt;
        float complaintResolvedFloat = (float) complaintResolvedInt / (float) totalInt;

        ArrayList<PieEntry> entries = new ArrayList<>();
        //if(pendingComplaintFloat>0)
        //{
            entries.add(new PieEntry(pendingComplaintFloat, "Pending Complaints"));
        //}
        //if(driverAddedFloat>0)
        //{
            entries.add(new PieEntry(driverAddedFloat, "AddDrive to Complaint"));
        //}
        //if(workInProgressFloat>0)
        //{
            entries.add(new PieEntry(workInProgressFloat, "WorkInProgress Complaint"));
        //}
        //if(workCompletedFloat>0)
        //{
            entries.add(new PieEntry(workCompletedFloat, "WorkCompleted Complaints"));
        //}
        //if(complaintResolvedFloat>0)
        //{
            entries.add(new PieEntry(complaintResolvedFloat, "Resolved Complaints"));
        //}
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Complaint Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);

    }



}