package com.example.spuntech.ui.faults;

import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spuntech.CardAdapter;
import com.example.spuntech.Fault;
import com.example.spuntech.PeriodicCardAdapter;
import com.example.spuntech.PeriodicFault;
import com.example.spuntech.R;

import java.util.ArrayList;

public class myfaults<myfaultsViewModel> extends Fragment {

    Button bt;
    private MyfaultsViewModel mViewModel;
    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    LinearLayoutManager manager1 = new LinearLayoutManager(getContext());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(MyfaultsViewModel.class);
        final View root = inflater.inflate(R.layout.myfaults_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerallfaults1);
        recyclerView1 = root.findViewById(R.id.recyclerallperiodicfaults1);
        mViewModel.getList().observe((LifecycleOwner) requireContext(), faultlist);
        mViewModel.getList1().observe((LifecycleOwner) requireContext(), pfaultlist);

        return root;
    }
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

    }

    private Observer<ArrayList<Fault>> faultlist = new Observer<ArrayList<Fault>>() {
        @Override
        public void onChanged(ArrayList<Fault> faults) {
            CardAdapter adapter=new CardAdapter(getContext(),faults);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);

        }
    };

    private Observer<ArrayList<PeriodicFault>> pfaultlist = new Observer<ArrayList<PeriodicFault>>() {
        @Override
        public void onChanged(ArrayList<PeriodicFault> pfaults) {
            System.out.println(pfaults.size());
            PeriodicCardAdapter adapter=new PeriodicCardAdapter(getContext(),pfaults);
            recyclerView1.setLayoutManager(manager1);
            recyclerView1.setAdapter(adapter);

        }
    };


}