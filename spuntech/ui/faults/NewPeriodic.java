package com.example.spuntech.ui.faults;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.spuntech.Global;
import com.example.spuntech.PeriodicFault;
import com.example.spuntech.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewPeriodic extends Fragment {
    EditText etdate;
    EditText etname;
    EditText etdes;
    Button bt;
    java.util.Date myDate;
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    final Calendar myCalendar = Calendar.getInstance();
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    private NewPeriodicViewModel mViewModel;

    public static NewPeriodic newInstance() {
        return new NewPeriodic();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.new_periodic_fragment, container, false);

        etname = (EditText)root.findViewById(R.id.firstnameNewPeriodicFaultText);
        etdate = (EditText)root.findViewById(R.id.dateNewPeriodicFaultText);
        etdate.setFocusable(false);
        etname.setFocusable(false);
        etname.setText(Global.svar1);

        //unitSpinner
        bt = (Button)root.findViewById(R.id.butttonNewPeriodicFault) ;
        final Spinner spinner1 = (Spinner) root.findViewById(R.id.unitNewPeriodicFaultSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.unitArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        //subUnitSpinner
        final Spinner spinner2 = (Spinner) root.findViewById(R.id.subUnitNewPeriodicFaultSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.forOpenSubUnitArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        //faultypeSpinner
        final Spinner spinner3 = (Spinner) root.findViewById(R.id.faultTypeNewPeriodicFaultSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.faultTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        //treatTypeSpinner
        final Spinner spinner4 = (Spinner) root.findViewById(R.id.treatTypePeriodicFaultSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getContext(),
                R.array.faultTreatTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner4.setAdapter(adapter4);

        //notifySpinner
        final Spinner spinner5 = (Spinner) root.findViewById(R.id.notifyNewPeriodicSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getContext(),
                R.array.notiftyPerodicArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner5.setAdapter(adapter5);

        //handler1Spinner
        final Spinner spinner6 = (Spinner) root.findViewById(R.id.handler1NewPeriodicSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(getContext(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner6.setAdapter(adapter6);

        //handler2Spinner
        final Spinner spinner7 = (Spinner) root.findViewById(R.id.handler2NewPeriodicSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(getContext(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner7.setAdapter(adapter7);

        //handler3Spinner
        final Spinner spinner8 = (Spinner) root.findViewById(R.id.handler3NewPeriodicSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(getContext(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner8.setAdapter(adapter8);
        //prioritySpinner
        final Spinner spinner9 = (Spinner) root.findViewById(R.id.priorityNewPeriodicFaultSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(getContext(),
                R.array.priorityarray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner9.setAdapter(adapter9);

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

        etdate=(EditText)root.findViewById(R.id.dateNewPeriodicFaultText) ;
        etdes=(EditText)root.findViewById(R.id.descriptionNewPeriodicFault) ;
        etname=(EditText)root.findViewById(R.id.firstnameNewPeriodicFaultText) ;
        etdate.setText(currentDate);
        etdate.setInputType(InputType.TYPE_NULL);
        etdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                PeriodicFault fault= new PeriodicFault(spinner1.getSelectedItem().toString()
                        ,spinner6.getSelectedItem().toString(),
                        spinner7.getSelectedItem().toString(),spinner8.getSelectedItem().toString(),spinner2.getSelectedItem().toString(),
                        spinner3.getSelectedItem().toString(),spinner4.getSelectedItem().toString(),etname.getText().toString(),etdate.getText().toString(),
                        "????????????", spinner5.getSelectedItem().toString(), spinner9.getSelectedItem().toString(),etdes.getText().toString());
                myRef.child("periodicfaults").push().setValue(fault);
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewPeriodicViewModel.class);
        // TODO: Use the ViewModel
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

}