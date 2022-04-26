package com.example.spuntech.ui.faults;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spuntech.Fault;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AllfaultsViewModel extends ViewModel {
    MutableLiveData<ArrayList<Fault>> fault;
    ArrayList<String> lf12 = new ArrayList<String>();
    ArrayList<Fault> lf = new ArrayList<Fault>();
    Date date= Calendar.getInstance().getTime();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("faults");
    public AllfaultsViewModel() {
        fault = new MutableLiveData<>();
        init();
    }
    public MutableLiveData<ArrayList<Fault>> getList() {
        return fault;
    }
    public void init()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot ds:dataSnapshot.getChildren())
               {
                  Fault f= new Fault(ds.getKey(),ds.child("unit").getValue().toString(),ds.child("subUnit").getValue().toString(),
                          ds.child("faultType").getValue().toString(),ds.child("treatType").getValue().toString(),
                          ds.child("employee").getValue().toString(),ds.child("date").getValue().toString(),ds.child("status").getValue().toString(),
                          ds.child("handler1").getValue().toString(),ds.child("handler2").getValue().toString(),ds.child("handler3").getValue().toString(),
                          ds.child("priority").getValue().toString(),ds.child("description").getValue().toString());
                lf.add(f);
               }
                fault.setValue(lf);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                 Log.w("TAG", "Failed to read value.", error.toException());// Failed to read value
            }
        });
    }
}