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

import com.example.spuntech.CardAdapter;
import com.example.spuntech.Fault;
import com.example.spuntech.Global;
import com.example.spuntech.R;
import com.example.spuntech.ScrollViewAdapter;
import com.example.spuntech.UserView;

import java.util.ArrayList;

public class allfaults extends Fragment {
    Button bt;
    private AllfaultsViewModel mViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    public static allfaults newInstance() {
        return new allfaults();
    }
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(AllfaultsViewModel.class);
        final View root = inflater.inflate(R.layout.allfaults_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerallfaults);
        mViewModel.getList().observe((LifecycleOwner) requireContext(), faultlist);
        return root;
    }
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        navController = Navigation.findNavController(view);
        bt = view.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.SinonFragment1);
            }
        });
    }

    private Observer<ArrayList<Fault>> faultlist = new Observer<ArrayList<Fault>>() {
        @Override
        public void onChanged(ArrayList<Fault> faults) {
            System.out.println(faults.size());
            CardAdapter adapter=new CardAdapter(getContext(),faults);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);

            Log.d("global", "onChanged: " + Global.getUnit()+ Global.getSubUnit() + Global.getFaultType() + Global.getTreatType() +
                    Global.getHandler() + Global.getStatus() + Global.getNotify()
                    + Global.isCanSort());
            // 1 parameter
            if((Global.getUnit()!=null && (Global.getSubUnit()== null && Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit());
            }
            else if( ((Global.getSubUnit()!=null)  && (Global.getUnit()== null && Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && (Global.isCanSort()))
            {
                adapter.filter(Global.getSubUnit());
            }
            else if(((Global.getFaultType()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    &&
                    (Global.isCanSort()))
            {
                adapter.filter(Global.getFaultType());
            }
            else if(((Global.getTreatType()!=null)  && ((Global.getUnit()== null && Global.getSubUnit()==null &&
                    Global.getFaultType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null )))
                    && (Global.isCanSort()))
            {
                adapter.filter(Global.getTreatType());
            }
            else if(((Global.getHandler()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                    Global.getFaultType()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && (Global.isCanSort()))
            {
                adapter.filter(Global.getHandler());
            }
            else if(((Global.getStatus()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                    Global.getFaultType()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getPriority() == null ))
                    && (Global.isCanSort()))
            {
                adapter.filter(Global.getStatus());
            }
            else if(((Global.getPriority()!=null)  && (Global.getUnit()== null && Global.getSubUnit()==null &&
                    Global.getFaultType()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getStatus() == null ))
                    && (Global.isCanSort()))
            {
                adapter.filter(Global.getPriority()); //wa -> WorkersAdapter
            }
            // 2 parameter
            else if((  Global.getUnit()!=null && Global.getSubUnit() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit()); //wa -> WorkersAdapter
            }
            else if((  Global.getUnit()!=null && Global.getFaultType() != null && ( Global.getSubUnit()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getFaultType()); //wa -> WorkersAdapter
            }
            else if((  Global.getUnit()!=null && Global.getTreatType() != null && ( Global.getFaultType()==null &&
                    Global.getSubUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getTreatType()); //wa -> WorkersAdapter
            }
            else if((  Global.getUnit()!=null && Global.getHandler() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((  Global.getUnit()!=null && Global.getStatus() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((  Global.getUnit()!=null && Global.getPriority() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((  Global.getSubUnit()!=null && Global.getFaultType() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getFaultType()); //wa -> WorkersAdapter
            }
            else if((  Global.getSubUnit()!=null && Global.getTreatType() != null && ( Global.getFaultType()==null &&
                    Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getTreatType()); //wa -> WorkersAdapter
            }
            else if((  Global.getSubUnit()!=null && Global.getHandler() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getUnit() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((  Global.getSubUnit()!=null && Global.getStatus() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getUnit() == null && Global.getHandler() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((  Global.getSubUnit()!=null && Global.getPriority() != null && ( Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //
            else if((  Global.getTreatType()!=null && Global.getFaultType() != null && ( Global.getSubUnit()==null &&
                    Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getFaultType()); //wa -> WorkersAdapter
            }
            else if(( Global.getTreatType() != null  && Global.getStatus()!=null  && ( Global.getFaultType()==null &&
                    Global.getSubUnit()==null && Global.getHandler() == null && Global.getUnit() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((  Global.getTreatType()!=null && Global.getHandler() != null && ( Global.getFaultType()==null &&
                    Global.getUnit()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((  Global.getTreatType()!=null && Global.getPriority() != null && ( Global.getFaultType()==null &&
                    Global.getUnit()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //
            else if((  Global.getFaultType()!=null && Global.getHandler() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getPriority() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((  Global.getFaultType()!=null && Global.getStatus() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getPriority() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((  Global.getFaultType()!=null && Global.getPriority() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //
            else if((  Global.getHandler()!=null && Global.getStatus() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getFaultType() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((  Global.getHandler()!=null && Global.getPriority() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getFaultType() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //
            else if((  Global.getStatus()!=null && Global.getPriority() != null && ( Global.getUnit()==null &&
                    Global.getTreatType()==null && Global.getSubUnit() == null && Global.getFaultType() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            // 3 parameter
            else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getFaultType()!=null &&(
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getFaultType()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getTreatType()!=null &&(
                    Global.getFaultType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getTreatType()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getHandler()!=null &&(
                    Global.getTreatType()==null && Global.getFaultType() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getStatus()!=null &&(
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getFaultType() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getSubUnit() != null && Global.getPriority()!=null &&(
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getSubUnit(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getTreatType()!=null &&(
                    Global.getPriority()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getSubUnit() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getTreatType()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getHandler()!=null &&(
                    Global.getPriority()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getSubUnit() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getStatus()!=null &&(
                    Global.getPriority()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getSubUnit() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getFaultType() != null && Global.getPriority()!=null &&(
                    Global.getHandler()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getSubUnit() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getFaultType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getTreatType() != null && Global.getHandler()!=null &&(
                    Global.getFaultType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getTreatType() != null && Global.getStatus()!=null &&(
                    Global.getFaultType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getTreatType() != null && Global.getPriority()!=null &&(
                    Global.getFaultType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getTreatType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getHandler() != null && Global.getStatus()!=null &&(
                    Global.getFaultType()==null && Global.getSubUnit() == null && Global.getTreatType() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getHandler() != null && Global.getPriority()!=null &&(
                    Global.getFaultType()==null && Global.getSubUnit() == null && Global.getStatus() == null && Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((    Global.getUnit()!=null && Global.getStatus() != null && Global.getPriority()!=null &&(
                    Global.getFaultType()==null && Global.getSubUnit() == null && Global.getHandler() == null && Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getTreatType()!=null && (
                    Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getTreatType()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getHandler()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getStatus()!=null && (
                    Global.getUnit()==null && Global.getHandler() == null && Global.getTreatType() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getFaultType()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getFaultType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getTreatType()!=null && Global.getHandler()!=null && (
                    Global.getUnit()==null && Global.getPriority() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getTreatType()!=null && Global.getStatus()!=null && (
                    Global.getUnit()==null && Global.getPriority() == null && Global.getHandler() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getTreatType()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getTreatType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getHandler()!=null && Global.getStatus()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getPriority() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getHandler()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getSubUnit() != null  && Global.getStatus()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getHandler() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getFaultType() != null  && Global.getTreatType()!=null && Global.getHandler()!=null && (
                    Global.getUnit()==null && Global.getStatus() == null && Global.getSubUnit() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((     Global.getFaultType() != null  && Global.getTreatType()!=null && Global.getStatus()!=null && (
                    Global.getUnit()==null && Global.getHandler() == null && Global.getSubUnit() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((     Global.getFaultType() != null  && Global.getTreatType()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getHandler() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getTreatType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getFaultType() != null  && Global.getHandler()!=null && Global.getStatus()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getSubUnit() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((     Global.getFaultType() != null  && Global.getHandler()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getFaultType() != null  && Global.getStatus()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getTreatType() == null && Global.getSubUnit() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getTreatType() != null  && Global.getHandler()!=null && Global.getStatus()!=null && (
                    Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((     Global.getTreatType() != null  && Global.getHandler()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getTreatType() != null  && Global.getStatus()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((     Global.getHandler() != null  && Global.getStatus()!=null && Global.getPriority()!=null && (
                    Global.getUnit()==null && Global.getFaultType() == null && Global.getSubUnit() == null && Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getHandler(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //4 parameters

            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && (
                    Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null
                    && (Global.getTreatType() == null && Global.getStatus() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getStatus()!=null
                    && (Global.getTreatType() == null && Global.getHandler() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getPriority()!=null
                    && (Global.getTreatType() == null && Global.getStatus() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getPriority()); //wa -> WorkersAdapter
            }
            //change
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null
                    && (Global.getFaultType() == null && Global.getStatus() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getStatus()!=null
                    && (Global.getFaultType() == null && Global.getHandler() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getPriority()!=null
                    && (Global.getFaultType() == null && Global.getHandler() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                    && (Global.getFaultType() == null && Global.getTreatType() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getHandler()!=null && Global.getPriority()!=null
                    && (Global.getFaultType() == null && Global.getTreatType() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getHandler(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getFaultType() == null && Global.getTreatType() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null
                    && (Global.getSubUnit() == null && Global.getStatus() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getStatus()!=null
                    && (Global.getSubUnit() == null && Global.getHandler() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getPriority()!=null
                    && (Global.getSubUnit() == null && Global.getStatus() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getPriority()); //wa -> WorkersAdapter
            }
            //change
            else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                    && (Global.getSubUnit() == null && Global.getPriority() == null && Global.getTreatType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getPriority()!=null
                    && (Global.getSubUnit() == null && Global.getStatus() == null && Global.getTreatType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getHandler(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getFaultType()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getSubUnit() == null && Global.getHandler() == null && Global.getTreatType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                    && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getPriority()!=null
                    && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getHandler(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getTreatType()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getSubUnit() == null && Global.getFaultType() == null && Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getHandler(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null
                    && (Global.getUnit() == null && Global.getStatus() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getStatus()!=null
                    && (Global.getUnit() == null && Global.getHandler() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getHandler() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                    && (Global.getUnit() == null && Global.getTreatType() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getTreatType() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getHandler(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getTreatType() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            //change 4
            else if((Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                    && (Global.getUnit() == null && Global.getPriority() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getStatus() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getHandler(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getHandler() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getSubUnit()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getTreatType() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getHandler(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null
                    && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getHandler(), Global.getStatus()); //wa -> WorkersAdapter
            }
            else if((Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getHandler(), Global.getPriority()); //wa -> WorkersAdapter
            }
            //change 1
            else if((Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getFaultType()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(), Global.getHandler(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getTreatType()!=null && Global.getHandler()!=null && Global.getStatus()!=null && Global.getPriority()!=null
                    && (Global.getUnit() == null && Global.getSubUnit() == null && Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getTreatType(), Global.getHandler(), Global.getStatus(), Global.getPriority()); //wa -> WorkersAdapter
            }
            //5 parameters
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&  Global.getHandler() != null &&
                    ( Global.getStatus() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getTreatType()!=null &&  Global.getStatus() != null
                    && ( Global.getHandler() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getTreatType()!=null &&  Global.getPriority() != null
                    && ( Global.getHandler() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null
                    && ( Global.getTreatType() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getHandler()!=null &&  Global.getPriority() != null
                    && ( Global.getTreatType() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getTreatType() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null
                    && ( Global.getFaultType() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getPriority() != null
                    && ( Global.getFaultType() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getFaultType() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getHandler()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getFaultType() == null && Global.getTreatType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getHandler(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null
                    && ( Global.getSubUnit() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getPriority() != null
                    && ( Global.getSubUnit() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getSubUnit() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getSubUnit() == null && Global.getTreatType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getSubUnit() == null && Global.getFaultType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null
                    && ( Global.getUnit() == null && Global.getPriority() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getPriority() != null
                    && ( Global.getUnit() == null && Global.getStatus() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getUnit() == null && Global.getHandler() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getHandler()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getUnit() == null && Global.getTreatType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getHandler(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getSubUnit()!=null && Global.getTreatType()!=null && Global.getHandler()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getUnit() == null && Global.getFaultType() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getTreatType(), Global.getHandler(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getFaultType()!=null && Global.getTreatType()!=null && Global.getHandler()!=null &&
                    Global.getStatus()!=null &&  Global.getPriority() != null
                    && ( Global.getUnit() == null && Global.getSubUnit() == null )) && Global.isCanSort())
            {
                adapter.filter(Global.getFaultType(), Global.getTreatType(), Global.getHandler(), Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //6 parameters
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getTreatType()!=null &&  Global.getHandler() != null &&
                    Global.getStatus() != null && ( Global.getPriority() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler(),Global.getStatus()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getTreatType()!=null &&  Global.getHandler() != null &&
                    Global.getPriority() != null && ( Global.getStatus() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getTreatType()!=null &&  Global.getStatus() != null &&
                    Global.getPriority() != null && ( Global.getHandler() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null &&
                    Global.getPriority() != null && ( Global.getTreatType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getHandler(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null &&
                    Global.getPriority() != null && ( Global.getFaultType() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getTreatType(), Global.getHandler(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null &&
                    Global.getPriority() != null && ( Global.getSubUnit() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if(( Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&
                    Global.getHandler()!=null &&  Global.getStatus() != null &&
                    Global.getPriority() != null && ( Global.getUnit() == null ))
                    && Global.isCanSort())
            {
                adapter.filter(Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(), Global.getHandler(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            //7 parameters
            else if(( Global.getUnit()!=null && Global.getSubUnit()!=null && Global.getFaultType()!=null && Global.getTreatType()!=null &&  Global.getHandler() != null &&
                    Global.getStatus() != null && Global.getPriority() != null ) && Global.isCanSort())
            {
                adapter.filter(Global.getUnit(), Global.getSubUnit(), Global.getFaultType(), Global.getTreatType(),Global.getHandler(),Global.getStatus(),Global.getPriority()); //wa -> WorkersAdapter
            }
            else if((Global.getUnit()==null && Global.getSubUnit()== null && Global.getFaultType()==null &&
                    Global.getTreatType()==null && Global.getHandler() == null && Global.getStatus() == null && Global.getPriority() == null )
                    && (Global.isCanSort()))
            {
                adapter.filter();
            }


        }
    };

    
}