package com.example.spuntech.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spuntech.R;
import com.example.spuntech.User;
import com.example.spuntech.UserCardAdapter;

import java.util.ArrayList;

public class deluser extends Fragment {

    private DeluserViewModel mViewModel;
    private RecyclerView recyclerView ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(DeluserViewModel.class);

        View root = inflater.inflate(R.layout.deluser_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerdelusers);
        mViewModel.getList().observe((LifecycleOwner) requireContext(),userlist );


        return root;
    }
    private Observer<ArrayList<User>> userlist = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> users) {
            UserCardAdapter adapter=new UserCardAdapter(getContext(),users);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);

        }
    };





}
