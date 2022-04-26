package com.example.spuntech.ui.users;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spuntech.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeluserViewModel extends ViewModel {
    
    
    MutableLiveData<ArrayList<User>> users;
    ArrayList<User> uf = new ArrayList<User>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("users");

    public DeluserViewModel() {
        
        users = new MutableLiveData<>();
        
        init();
        
    }

    public  MutableLiveData<ArrayList<User>> getList() {
        return users;

    }
    
    public void init()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                   User f= new User(ds.child("username").getValue().toString(),ds.child("firstname").getValue().toString(),
                           ds.child("lastname").getValue().toString(),ds.child("pass").getValue().toString()
                           ,ds.child("phone").getValue().toString(),ds.child("email").getValue().toString(),ds.child("level").getValue().toString());
                    uf.add(f);

                }
                users.setValue(uf);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}
