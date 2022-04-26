package com.example.spuntech.ui.faults;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spuntech.Fault;
import com.example.spuntech.Global;
import com.example.spuntech.PeriodicFault;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyfaultsViewModel extends ViewModel {
    MutableLiveData<ArrayList<Fault>> fault;
    MutableLiveData<ArrayList<PeriodicFault>> pfault;
    ArrayList<PeriodicFault> lpf = new ArrayList<PeriodicFault>();
    ArrayList<Fault> lf = new ArrayList<Fault>();
    Date date = Calendar.getInstance().getTime();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("faults");
    DatabaseReference myRef1 = database.getReference().child("periodicfaults");
    public MyfaultsViewModel() {
        fault = new MutableLiveData<>();
        pfault = new MutableLiveData<>();
        init();
        init1();
    }
    public MutableLiveData<ArrayList<PeriodicFault>> getList1() {
        return pfault;
    }
    public MutableLiveData<ArrayList<Fault>> getList() {
        return fault;
    }
    public void init() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if (ds.child("handler1").getValue().toString().equals(Global.svar1) || ds.child("handler2").getValue().toString().equals(Global.svar1) || ds.child("handler3").getValue().toString().equals(Global.svar1)) {
                        Fault f = new Fault(ds.getKey(), ds.child("unit").getValue().toString(), ds.child("subUnit").getValue().toString(),
                                ds.child("faultType").getValue().toString(), ds.child("treatType").getValue().toString(),
                                ds.child("employee").getValue().toString(), ds.child("date").getValue().toString(), ds.child("status").getValue().toString(),
                                ds.child("handler1").getValue().toString(), ds.child("handler2").getValue().toString(), ds.child("handler3").getValue().toString(),
                                ds.child("priority").getValue().toString(), ds.child("description").getValue().toString());
                        lf.add(f);
                        fault.setValue(lf); } } }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException()); // Failed to read value
            }
        });


    }

    public void init1() {
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if (ds.child("handler1").getValue().toString().equals(Global.svar1) || ds.child("handler2").getValue().toString().equals(Global.svar1) || ds.child("handler3").getValue().toString().equals(Global.svar1)) {
                        PeriodicFault f = new PeriodicFault(ds.getKey(),ds.child("unit").getValue().toString(),
                                ds.child("handler1").getValue().toString(),ds.child("handler2").getValue().toString(),
                                ds.child("handler3").getValue().toString(),ds.child("subUnit").getValue().toString(),ds.child("faultType").getValue().toString(),
                                ds.child("treatType").getValue().toString(),ds.child("openFaultEmployee").getValue().toString(),ds.child("date").getValue().toString(),
                                ds.child("status").getValue().toString(),ds.child("notify").getValue().toString(),ds.child("priority").getValue().toString(),ds.child("description").getValue().toString());
                        lpf.add(f);
                        pfault.setValue(lpf);
                    }
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