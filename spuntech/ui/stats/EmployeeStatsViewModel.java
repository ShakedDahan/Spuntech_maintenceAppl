package com.example.spuntech.ui.stats;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spuntech.myEmployeeStats;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeStatsViewModel extends ViewModel {
    MutableLiveData<ArrayList<myEmployeeStats>> myemployeestat;
    ArrayList<myEmployeeStats> mes = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("faults");
    DatabaseReference myRef1 = database.getReference().child("users");
    ArrayList<String> users=new ArrayList<>();
    int count=0;
    int count1=0;

    public EmployeeStatsViewModel() {
        myemployeestat = new MutableLiveData<>();
        init();

    }

    public MutableLiveData<ArrayList<myEmployeeStats>> getList() {
        return myemployeestat;

    }

    public void init() {
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
                for(int i =0;i<users.size();i++) {
                    count = 0;
                    count1 = 0;
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Log.d("TAGeeeeeeeeee", users.get(i) + "");
                        if (ds.child("handler1").getValue().toString().equals(users.get(i)) || ds.child("handler2").getValue().toString().equals(users.get(i)) || ds.child("handler3").getValue().toString().equals(users.get(i))) {
                            count = count + 1;
                            if (ds.child("status").getValue().toString().equals("בטיפול"))
                                count1 = count1 + 1;
                        }

                    }
                    myEmployeeStats m = new myEmployeeStats(users.get(i), count, count1);

                    mes.add(m);
                    myemployeestat.setValue(mes);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }
}