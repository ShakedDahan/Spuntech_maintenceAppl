package com.example.spuntech;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SinonFragment1 extends Fragment {

    private SinonViewModel1 mViewModel;

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    Spinner spinner7;
    public static SinonFragment1 newInstance() {
        return new SinonFragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Global.setUnit(null);
        Global.setSubUnit(null);
        Global.setFaultType(null);
        Global.setTreatType(null);
        Global.setHandler(null);
        Global.setStatus(null);
        Global.setNotify(null);
        Global.setPriority(null);
        Global.setCanSort(false);

        final View root = inflater.inflate(R.layout.sort_by_fault_fragment, container, false);
        //unitSpinner
         spinner1 = (Spinner) root.findViewById(R.id.sortByunit);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.unitArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        //subUnitSpinner
         spinner2 = (Spinner) root.findViewById(R.id.sortBySubUnit);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.forOpenSubUnitArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter1);

        //faultypeSpinner
         spinner3 = (Spinner) root.findViewById(R.id.sortByFault);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.faultTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        //treatTypeSpinner
         spinner4 = (Spinner) root.findViewById(R.id.sortByTreatType);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getContext(),
                R.array.faultTreatTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner4.setAdapter(adapter4);

        //handlerSpinner
         spinner5 = (Spinner) root.findViewById(R.id.sortByHandler);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getContext(),
                R.array.handlersArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner5.setAdapter(adapter5);

        //statusHandler
         spinner6 = (Spinner) root.findViewById(R.id.sortByStatus);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(getContext(),
                R.array.statusArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner6.setAdapter(adapter6);

        //priorityHandler
        spinner7 = (Spinner) root.findViewById(R.id.sortByPriority);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(getContext(),
                R.array.priorityarray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner7.setAdapter(adapter7);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setUnit(spinner1.getSelectedItem().toString());
                Log.d("checkNull", spinner1.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner1.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setUnit(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setUnit(null);
            }



        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setSubUnit(spinner2.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner2.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setSubUnit(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setSubUnit(null);
            }



        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setFaultType(spinner3.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner3.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setFaultType(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setFaultType(null);
            }



        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setTreatType(spinner4.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner4.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setTreatType(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setTreatType(null);
            }



        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setHandler(spinner5.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner5.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setHandler(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setHandler(null);
            }



        });
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setStatus(spinner6.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner6.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setStatus(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setStatus(null);
            }



        });

        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
                Global.setPriority(spinner7.getSelectedItem().toString());
                Global.setCanSort(true);
                if(spinner7.getSelectedItem().toString().equals(""))
                {
                    /*fix idea*/
                    Global.setPriority(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Global.setPriority(null);
            }



        });

        return root;
    }

    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                navController.navigate(R.id.action_SinonFragment1_to_nav_allfaults);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SinonViewModel1.class);
        // TODO: Use the ViewModel
    }

}