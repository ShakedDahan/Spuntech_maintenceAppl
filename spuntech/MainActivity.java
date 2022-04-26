package com.example.spuntech;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    Button Logbtn;
    EditText user;
    EditText pass;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("users");
    Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.inputEmail);
        pass=(EditText)findViewById(R.id.inputPassword);



    }

    public void OpenMenu(View view) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    String username = ds.child("username").getValue().toString();

                    if (user.getText().toString().equals(username)) {
                        if (pass.getText().toString().equals(ds.child("pass").getValue().toString())) {
                            final String firstnameuser = ds.child("firstname").getValue().toString();
                            final String lastnameuser = ds.child("lastname").getValue().toString();
                            final String level = ds.child("level").getValue().toString();
                            Log.d("TAGname", firstnameuser);
                            Global.svar1 = firstnameuser + " " + lastnameuser;
                            Global.svar2 = level;
                            Log.d("Firebase", "token " + FirebaseInstanceId.getInstance().getToken());
                            final Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(intent);


                        }
                        else {
                            pass.setError("סיסמא לא חוקית");
                        }

                    }
                    else if(user.getText().length()==0&&pass.getText().length()==0) {
                        user.setError("שם משתמש לא חוקי");
                        pass.setError("סיסמא לא חוקית");
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
