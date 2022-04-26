package com.example.spuntech;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import com.example.spuntech.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FullDetailsFault extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    EditText etdate;
    NavController navController;
    EditText openFaultName;
    EditText description;
    DatabaseReference myRef;
    String fromWhere;
    String matchKey;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    Spinner spinner7;
    Spinner spinner8;
    Spinner spinner9;
    Spinner spinner10;
    TextView notifyText;
    Button updateFaultDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_details_fault);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        openFaultName = (EditText)findViewById(R.id.firstnamefd);
        description = (EditText)findViewById(R.id.descriptionfd);
        updateFaultDataBtn = (Button) findViewById(R.id.button10fd);
        etdate = (EditText) findViewById(R.id.datefd);
        Intent intent = getIntent();
        matchKey = intent.getStringExtra("IDofFault");
        fromWhere = intent.getStringExtra("fromWhere");
        openFaultName.setFocusable(false);
        etdate.setFocusable(false);
        //unitSpinner
         spinner1 = (Spinner) findViewById(R.id.unitNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.unitArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        //subUnitSpinner
         spinner2 = (Spinner) findViewById(R.id.subUnitNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.forOpenSubUnitArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        //faultypeSpinner
         spinner3 = (Spinner) findViewById(R.id.faultTypeNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.faultTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        //treatTypeSpinner
         spinner4 = (Spinner) findViewById(R.id.treatTypeNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.faultTreatTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner4.setAdapter(adapter4);

        //handler1Spinner
         spinner5 = (Spinner) findViewById(R.id.handler1NewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner5.setAdapter(adapter5);

        //handler2Spinner
         spinner6 = (Spinner) findViewById(R.id.handler2NewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner6.setAdapter(adapter6);

        //handler3Spinner
         spinner7 = (Spinner) findViewById(R.id.handler3NewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner7.setAdapter(adapter7);

        //prioritySpinner
         spinner8 = (Spinner) findViewById(R.id.priorityNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.priorityarray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner8.setAdapter(adapter8);

        //StatusSpinner
         spinner9 = (Spinner) findViewById(R.id.statusNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.statusArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner9.setAdapter(adapter9);

        //NotifySpinner
         spinner10 = (Spinner) findViewById(R.id.notifyNewFaultSpinnerfd);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this.getApplication(),
                R.array.notiftyPerodicArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner10.setAdapter(adapter10);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });

        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    ////////////////////////selecting faults or periodic faults
        if(fromWhere.equals("fault"))
        {
            myRef = database.getReference().child("faults");
            notifyText = (TextView)findViewById(R.id.notifyNewFaultTextfd);
            notifyText.setVisibility(View.GONE);
            spinner10.setVisibility(View.GONE);
            init1();
        }
        else
        {
            myRef = database.getReference().child("periodicfaults");
            init2();
        }

        ///////////////////////DATE CALENDER SET////////////////////////////
        etdate=(EditText) findViewById(R.id.datefd) ;
        etdate.setInputType(InputType.TYPE_NULL);
        etdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        updateFaultDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromWhere.equals("fault")) {
                    myRef = database.getReference().child("faults").child(matchKey);
                    myRef.child("date").setValue(etdate.getText().toString());
                    myRef.child("unit").setValue(spinner1.getSelectedItem().toString());
                    myRef.child("subUnit").setValue(spinner2.getSelectedItem().toString());
                    myRef.child("faultType").setValue(spinner3.getSelectedItem().toString());
                    myRef.child("treatType").setValue(spinner4.getSelectedItem().toString());
                    myRef.child("handler1").setValue(spinner5.getSelectedItem().toString());
                    myRef.child("handler2").setValue(spinner6.getSelectedItem().toString());
                    myRef.child("handler3").setValue(spinner7.getSelectedItem().toString());
                    myRef.child("priority").setValue(spinner8.getSelectedItem().toString());
                    myRef.child("status").setValue(spinner9.getSelectedItem().toString());
                    myRef.child("description").setValue(description.getText().toString());


                }
                else
                {
                    myRef = database.getReference().child("periodicfaults").child(matchKey);
                    myRef.child("date").setValue(etdate.getText().toString());
                    myRef.child("unit").setValue(spinner1.getSelectedItem().toString());
                    myRef.child("subUnit").setValue(spinner2.getSelectedItem().toString());
                    myRef.child("faultType").setValue(spinner3.getSelectedItem().toString());
                    myRef.child("treatType").setValue(spinner4.getSelectedItem().toString());
                    myRef.child("handler1").setValue(spinner5.getSelectedItem().toString());
                    myRef.child("handler2").setValue(spinner6.getSelectedItem().toString());
                    myRef.child("handler3").setValue(spinner7.getSelectedItem().toString());
                    myRef.child("priority").setValue(spinner8.getSelectedItem().toString());
                    myRef.child("status").setValue(spinner9.getSelectedItem().toString());
                    myRef.child("notify").setValue(spinner10.getSelectedItem().toString());
                    myRef.child("description").setValue(description.getText().toString());
                }
                finish();
                Intent intent = new Intent(getApplication(), Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etdate.setText(sdf.format(myCalendar.getTime()));
    }
    public void init1()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                        if(ds.getKey().equals(matchKey))
                        {
                            openFaultName.setText(ds.child("employee").getValue().toString());
                            description.setText(ds.child("description").getValue().toString());
                            etdate.setText(ds.child("date").getValue().toString());
                            ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)spinner1.getAdapter();
                            spinner1.setSelection(array_spinner.getPosition(ds.child("unit").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner2.getAdapter();
                            spinner2.setSelection(array_spinner.getPosition(ds.child("subUnit").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner3.getAdapter();
                            spinner3.setSelection(array_spinner.getPosition(ds.child("faultType").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner4.getAdapter();
                            spinner4.setSelection(array_spinner.getPosition(ds.child("treatType").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner5.getAdapter();
                            spinner5.setSelection(array_spinner.getPosition(ds.child("handler1").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner6.getAdapter();
                            spinner6.setSelection(array_spinner.getPosition(ds.child("handler2").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner7.getAdapter();
                            spinner7.setSelection(array_spinner.getPosition(ds.child("handler3").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner8.getAdapter();
                            spinner8.setSelection(array_spinner.getPosition(ds.child("priority").getValue().toString()));
                            array_spinner=(ArrayAdapter<String>)spinner9.getAdapter();
                            spinner9.setSelection(array_spinner.getPosition(ds.child("status").getValue().toString()));


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
    public void init2()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(ds.getKey().equals(matchKey))
                    {
                        openFaultName.setText(ds.child("openFaultEmployee").getValue().toString());
                        description.setText(ds.child("description").getValue().toString());
                        etdate.setText(ds.child("date").getValue().toString());
                        ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)spinner1.getAdapter();
                        spinner1.setSelection(array_spinner.getPosition(ds.child("unit").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner2.getAdapter();
                        spinner2.setSelection(array_spinner.getPosition(ds.child("subUnit").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner3.getAdapter();
                        spinner3.setSelection(array_spinner.getPosition(ds.child("faultType").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner4.getAdapter();
                        spinner4.setSelection(array_spinner.getPosition(ds.child("treatType").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner5.getAdapter();
                        spinner5.setSelection(array_spinner.getPosition(ds.child("handler1").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner6.getAdapter();
                        spinner6.setSelection(array_spinner.getPosition(ds.child("handler2").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner7.getAdapter();
                        spinner7.setSelection(array_spinner.getPosition(ds.child("handler3").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner8.getAdapter();
                        spinner8.setSelection(array_spinner.getPosition(ds.child("priority").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner9.getAdapter();
                        spinner9.setSelection(array_spinner.getPosition(ds.child("status").getValue().toString()));
                        array_spinner=(ArrayAdapter<String>)spinner10.getAdapter();
                        spinner10.setSelection(array_spinner.getPosition(ds.child("notify").getValue().toString()));

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