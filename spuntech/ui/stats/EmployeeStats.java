package com.example.spuntech.ui.stats;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spuntech.EmployeeByNameCountAdapter;
import com.example.spuntech.Global;
import com.example.spuntech.R;
import com.example.spuntech.myEmployeeStats;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public class EmployeeStats extends Fragment {
    GraphView graph ;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("faults");
    DatabaseReference myRef1 = database.getReference().child("users");
    int count=0;
    int count1=0;
    ArrayList<String> users=new ArrayList<>();

    private EmployeeStatsViewModel employeeStatsViewModel;
    private RecyclerView recyclerView;
    public static EmployeeStats newInstance() {
        return new EmployeeStats();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        employeeStatsViewModel =
                ViewModelProviders.of(this).get(EmployeeStatsViewModel.class);


        final View root= inflater.inflate(R.layout.employee_stats_fragment, container, false);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    users.add(ds.child("firstname").getValue().toString()+" "+ds.child("lastname").getValue().toString());
                    Log.d("users", users.size()+"");


                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i =0;i<users.size()+1;i++) {
                    count = 0;
                    count1 = 0;
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(i<users.size()) {
                            if (ds.child("handler1").getValue().toString().equals(users.get(i)) || ds.child("handler2").getValue().toString().equals(users.get(i)) || ds.child("handler3").getValue().toString().equals(users.get(i))) {
                                count = count + 1;
                                if (ds.child("status").getValue().toString().equals("בטיפול"))
                                    count1 = count1 + 1;
                            }
                        }
                    }
                    graph = root.findViewById(R.id.graph1);
                    BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                            new DataPoint(i+1, count),
                    });
                    BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[]{
                            new DataPoint(i+1, count1),
                    });

                    graph.addSeries(series);
                    graph.addSeries(series1);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setMinX(0);
                    graph.getViewport().setMaxX(4);
                    series.setColor(Color.GREEN);
                    series1.setColor(Color.RED);
                    series.setAnimated(true);
                    series.setDrawValuesOnTop(true);
                    series.setValuesOnTopColor(Color.BLACK);
                    series1.setAnimated(true);
                    series1.setDrawValuesOnTop(true);
                    series1.setValuesOnTopColor(Color.BLACK);
                    Global.count = 0;
                    Global.count1 = 0;
                    Global.count2 = 0;
                    Global.count3 = 0;

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


        Log.d("TAG2222222", Global.count+"");




        recyclerView = root.findViewById(R.id.myEmployeeFaultStatsRecyclerView);
        employeeStatsViewModel.getList().observe((LifecycleOwner) requireContext(), myemployeestats);

        return root;
    }
    private Observer<ArrayList<myEmployeeStats>> myemployeestats = new Observer<ArrayList<myEmployeeStats>>() {
        @Override
        public void onChanged(ArrayList<myEmployeeStats> myEmployeeStatsArrayList) {
            System.out.println(myEmployeeStatsArrayList.size());
            EmployeeByNameCountAdapter adapter=new EmployeeByNameCountAdapter(getContext(),myEmployeeStatsArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        employeeStatsViewModel = ViewModelProviders.of(this).get(EmployeeStatsViewModel.class);
        // TODO: Use the ViewModel
    }


}