package com.example.spuntech.ui.faults;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spuntech.Global;
import com.example.spuntech.PeriodicCardAdapter;
import com.example.spuntech.PeriodicFault;
import com.example.spuntech.R;

import java.util.ArrayList;
import java.util.Objects;

public class allperiodicfaults extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    private AllperiodicfaultsViewModel mViewModel1;
    NavController navController;
    ArrayList<PeriodicFault> pfaults1 = new ArrayList<>();
    Button bt;
    PeriodicCardAdapter adapter;


    public static allperiodicfaults newInstance() {
        return new allperiodicfaults();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel1 =
                ViewModelProviders.of(this).get(AllperiodicfaultsViewModel.class);
        View root= inflater.inflate(R.layout.allperiodicfaults_fragment, container, false);



        recyclerView = root.findViewById(R.id.recyclerallperiodicfaults);
        mViewModel1.getList().observe((LifecycleOwner) requireContext(), pfaultlist);
        return root;
    }
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        navController = Navigation.findNavController(view);
        bt = view.findViewById(R.id.peridoicSortButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.periodicSinonFragment);
            }
        });

    }
    private Observer<ArrayList<PeriodicFault>> pfaultlist = new Observer<ArrayList<PeriodicFault>>() {

        @Override
        public void onChanged(ArrayList<PeriodicFault> pfaults) {
            System.out.println(pfaults.size());
            PeriodicCardAdapter adapter=new PeriodicCardAdapter(getContext(),pfaults);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            Log.d("global", "onChanged: " + Global.getUnit()+ Global.getSubUnit() + Global.getFaultType() + Global.getTreatType() +
                    Global.getHandler() + Global.getStatus() + Global.getNotify()
                    + Global.isCanSort());
                // 1 parameter
                    if((Global.getUnit()!=null && (Global.getSubUnit()== null && Global.getFaultType()==null &&
                        Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                        && Global.isCanSort())
                    {
                       adapter.filter(Global.getUnit());
                    }
                    else if( ((Global.getSubUnit()!=null)  && (Global.getUnit()== null && Global.getFaultType()==null &&
                        Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && (Global.isCanSort()))
                    {
                        adapter.filter(Global.getSubUnit());
                    }
                    else if(((Global.getFaultType()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                                Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                                &&
                               (Global.isCanSort()))
                    {
                         adapter.filter(Global.getFaultType());
                    }
                        else if(((Global.getTreatType()!=null)  && ((Global.getUnit()== null && Global.getSubUnit()==null &&
                                Global.getFaultType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null )))
                                && (Global.isCanSort()))
                    {
                             adapter.filter(Global.getTreatType());
                    }
                        else if(((Global.getHandler()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                                Global.getFaultType()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getNotify() == null ))
                                && (Global.isCanSort()))
                    {
                         adapter.filter(Global.getHandler());
                    }
                        else if(((Global.getStatus()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                        Global.getFaultType()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getNotify() == null ))
                                && (Global.isCanSort()))
                    {
                       adapter.filter(Global.getStatus());
                    }
                        else if(((Global.getNotify()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                        Global.getFaultType()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getStatus() == null ))
                        && (Global.isCanSort()))
                    {
                        adapter.filter(Global.getNotify()); //wa -> WorkersAdapter
                    }
                // 2 parameter
            else if((  Global.getUnit()!=null && Global.getSubUnit() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit()); //wa -> WorkersAdapter
            }
                    else if((  Global.getUnit()!=null && Global.getFaultType() != null && ( Global.getSubUnit()==null &&
                            Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getFaultType()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getUnit()!=null && Global.getTreatType() != null && ( Global.getFaultType()==null &&
                            Global.getSubUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getTreatType()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getUnit()!=null && Global.getHandler() != null && ( Global.getFaultType()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getUnit()!=null && Global.getStatus() != null && ( Global.getFaultType()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getUnit()!=null && Global.getNotify() != null && ( Global.getFaultType()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getSubUnit()!=null && Global.getFaultType() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getFaultType()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getSubUnit()!=null && Global.getTreatType() != null && ( Global.getFaultType()==null &&
                            Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getTreatType()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getSubUnit()!=null && Global.getHandler() != null && ( Global.getFaultType()==null &&
                            Global.getTreatType()==null && Global.getUnit() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getSubUnit()!=null && Global.getStatus() != null && ( Global.getFaultType()==null &&
                            Global.getTreatType()==null && Global.getUnit() == null && Global.getHandler() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getSubUnit()!=null && Global.getNotify() != null && ( Global.getFaultType()==null &&
                            Global.getTreatType()==null && Global.getUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //
                    else if((  Global.getTreatType()!=null && Global.getFaultType() != null && ( Global.getSubUnit()==null &&
                            Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getFaultType()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getTreatType() != null  && Global.getStatus()!=null  && ( Global.getFaultType()==null &&
                            Global.getSubUnit()==null && Global.getHandler() == null && Global.getUnit() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getTreatType()!=null && Global.getHandler() != null && ( Global.getFaultType()==null &&
                            Global.getUnit()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getTreatType()!=null && Global.getNotify() != null && ( Global.getFaultType()==null &&
                            Global.getUnit()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //
                    else if((  Global.getFaultType() !=null && Global.getHandler() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getNotify() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getFaultType()!=null && Global.getStatus() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getNotify() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getFaultType()!=null && Global.getNotify() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //
                    else if((  Global.getHandler()!=null && Global.getStatus() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getFaultType() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((  Global.getHandler()!=null && Global.getNotify() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getFaultType() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //
                    else if((  Global.getStatus()!=null && Global.getNotify() != null && ( Global.getUnit()==null &&
                            Global.getTreatType()==null && Global.getSubUnit() == null && Global.getFaultType() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
            // 3 parameter
            else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getFaultType()!=null &&(
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getFaultType()); //wa -> WorkersAdapter
            }
                else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getTreatType()!=null &&(
                            Global.getFaultType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getTreatType()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getHandler()!=null &&(
                            Global.getTreatType()==null && Global.getFaultType() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getStatus()!=null &&(
                            Global.getTreatType()==null && Global.getHandler() == null && Global.getFaultType() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getNotify()!=null &&(
                            Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getTreatType()!=null &&(
                            Global.getNotify()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getSubUnit() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getTreatType()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getHandler()!=null &&(
                            Global.getNotify()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getSubUnit() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getStatus()!=null &&(
                            Global.getNotify()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getSubUnit() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getNotify()!=null &&(
                            Global.getHandler()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getSubUnit() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getTreatType() != null && Global.getHandler()!=null &&(
                            Global.getFaultType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getTreatType() != null && Global.getStatus()!=null &&(
                            Global.getFaultType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getTreatType() != null && Global.getNotify()!=null &&(
                            Global.getFaultType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getTreatType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getHandler() != null && Global.getStatus()!=null &&(
                            Global.getFaultType()==null && Global.getSubUnit() == null && Global.getTreatType() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getHandler() != null && Global.getNotify()!=null &&(
                            Global.getFaultType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((    Global.getUnit()!=null && Global.getStatus() != null && Global.getNotify()!=null &&(
                            Global.getFaultType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getTreatType()!=null && (
                            Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getTreatType()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getHandler()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getStatus()!=null && (
                            Global.getUnit()==null && Global.getHandler() == null && Global.getTreatType() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getTreatType()!=null && Global.getHandler()!=null && (
                            Global.getUnit()==null && Global.getNotify() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getTreatType()!=null && Global.getStatus()!=null && (
                            Global.getUnit()==null && Global.getNotify() == null && Global.getHandler() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getTreatType()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getTreatType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getHandler()!=null && Global.getStatus()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getNotify() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getHandler()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getSubUnit() != null  && Global.getStatus()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getFaultType() != null  && Global.getTreatType()!=null && Global.getHandler()!=null && (
                            Global.getUnit()==null && Global.getStatus() == null && Global.getSubUnit() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getFaultType() != null  && Global.getTreatType()!=null && Global.getStatus()!=null && (
                            Global.getUnit()==null && Global.getHandler() == null && Global.getSubUnit() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getFaultType() != null  && Global.getTreatType()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getHandler() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getTreatType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getFaultType() != null  && Global.getHandler()!=null && Global.getStatus()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getSubUnit() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getFaultType() != null  && Global.getHandler()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getFaultType() != null  && Global.getStatus()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getTreatType() == null && Global.getSubUnit() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getTreatType() != null  && Global.getHandler()!=null && Global.getStatus()!=null && (
                            Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getTreatType() != null  && Global.getHandler()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getTreatType() != null  && Global.getStatus()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((     Global.getHandler() != null  && Global.getStatus()!=null && Global.getNotify()!=null && (
                            Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getHandler(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                //4 parameters

            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && (
                    Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType()); //wa -> WorkersAdapter
            }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null
                            && (Global.getTreatType() == null && Global.getStatus() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getStatus()!=null
                            && (Global.getTreatType() == null && Global.getHandler() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getNotify()!=null
                            && (Global.getTreatType() == null && Global.getStatus() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //change
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null
                            && (Global.getFaultType() == null && Global.getStatus() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getStatus()!=null
                            && (Global.getFaultType() == null && Global.getHandler() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getNotify()!=null
                            && (Global.getFaultType() == null && Global.getHandler() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                            && (Global.getFaultType() == null && Global.getTreatType() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getHandler()!=null && Global.getNotify()!=null
                            && (Global.getFaultType() == null && Global.getTreatType() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getHandler(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getFaultType() == null && Global.getTreatType() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null
                            && (Global.getSubUnit() == null && Global.getStatus() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getStatus()!=null
                            && (Global.getSubUnit() == null && Global.getHandler() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getNotify()!=null
                            && (Global.getSubUnit() == null && Global.getStatus() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //change
                    else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                            && (Global.getSubUnit() == null && Global.getNotify() == null && Global.getTreatType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getNotify()!=null
                            && (Global.getSubUnit() == null && Global.getStatus() == null && Global.getTreatType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getHandler(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getSubUnit() == null && Global.getHandler() == null && Global.getTreatType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                            && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getNotify()!=null
                            && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getHandler(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getTreatType()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getUnit()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getHandler(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null
                            && (Global.getUnit() == null && Global.getStatus() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getStatus()!=null
                            && (Global.getUnit() == null && Global.getHandler() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                            && (Global.getUnit() == null && Global.getTreatType() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getTreatType() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getHandler(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getTreatType() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //change 4
                    else if((Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                            && (Global.getUnit() == null && Global.getNotify() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getHandler(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getHandler() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getSubUnit()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getTreatType() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getHandler(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                            && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getNotify() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if((Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getHandler(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //change 1
                    else if((Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getFaultType()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(), Global.getHandler(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if((Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getNotify()!=null
                            && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getTreatType(), Global.getHandler(), Global.getStatus(), Global.getNotify()); //wa -> WorkersAdapter
                    }
                //5 parameters
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&  Global.getHandler() != null &&
                    ( Global.getStatus() == null && Global.getNotify() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
            }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getTreatType()!=null &&  Global.getStatus() != null
                            && ( Global.getHandler() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getTreatType()!=null &&  Global.getNotify() != null
                            && ( Global.getHandler() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null
                            && ( Global.getTreatType() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getHandler()!=null &&  Global.getNotify() != null
                            && ( Global.getTreatType() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getTreatType() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null
                            && ( Global.getFaultType() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getNotify() != null
                            && ( Global.getFaultType() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getFaultType() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getHandler()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getFaultType() == null && Global.getTreatType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getHandler(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null
                            && ( Global.getSubUnit() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getNotify() != null
                            && ( Global.getSubUnit() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getSubUnit() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getSubUnit() == null && Global.getTreatType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getSubUnit() == null && Global.getFaultType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null
                            && ( Global.getUnit() == null && Global.getNotify() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getNotify() != null
                            && ( Global.getUnit() == null && Global.getStatus() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getUnit() == null && Global.getHandler() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getUnit() == null && Global.getTreatType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getUnit() == null && Global.getFaultType() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null &&
                            Global.getStatus()!=null &&  Global.getNotify() != null
                            && ( Global.getUnit() == null && Global.getSubUnit() == null )) && Global.isCanSort())
                    {
                        adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getHandler(), Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
            //6 parameters
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getTreatType()!=null &&  Global.getHandler() != null &&
                    Global.getStatus() != null && ( Global.getNotify() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
                else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getTreatType()!=null &&  Global.getHandler() != null &&
                            Global.getNotify() != null && ( Global.getStatus() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getTreatType()!=null &&  Global.getStatus() != null &&
                            Global.getNotify() != null && ( Global.getHandler() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null &&
                            Global.getNotify() != null && ( Global.getTreatType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null &&
                            Global.getNotify() != null && ( Global.getFaultType() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null &&
                            Global.getNotify() != null && ( Global.getSubUnit() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                            Global.getHandler()!=null &&  Global.getStatus() != null &&
                            Global.getNotify() != null && ( Global.getUnit() == null ))
                            && Global.isCanSort())
                    {
                        adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
                    }
                    //7 parameters
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&  Global.getHandler() != null &&
                     Global.getStatus() != null && Global.getNotify() != null ) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler(),Global.getStatus(),Global.getNotify()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()==null && Global.getSubUnit()== null && Global.getFaultType()==null &&
                        Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getNotify() == null )
                        && (Global.isCanSort()))
            {
                adapter.filter();
            }




        }
    };


}