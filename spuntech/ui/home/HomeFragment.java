package com.example.spuntech.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.spuntech.Global;
import com.example.spuntech.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

   HorizontalScrollView horizontalScrollView;
    NavController navController;
    CardView cardnewfault;
    CardView cardallfault;
    CardView cardnewperi;
    CardView cardallperi;
    CardView cardnewuser;
    CardView carddeluser;
    CardView cardlogout;
    CardView cardmyfaults;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        cardnewfault = root.findViewById(R.id.newfault);
        cardallfault = root.findViewById(R.id.allfault);
        cardnewperi = root.findViewById(R.id.newperi);
        cardallperi = root.findViewById(R.id.allperi);
        cardnewuser = root.findViewById(R.id.newuser);
        carddeluser = root.findViewById(R.id.deluser);
        cardmyfaults = root.findViewById(R.id.myfaults);
        cardlogout = root.findViewById(R.id.cardLogout);
        return root;
    }
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        navController = Navigation.findNavController(view);

        cardnewfault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_newfault);
            }
        });
        cardallfault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(Global.svar2.equals("עובד אחזקה")))
                navController.navigate(R.id.nav_allfaults);
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setMessage("גישה נדחתה");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });
        cardnewperi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(Global.svar2.equals("עובד אחזקה")))
                navController.navigate(R.id.nav_NewPeriodic);
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setMessage("גישה נדחתה");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });
        cardallperi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(Global.svar2.equals("עובד אחזקה")))
                navController.navigate(R.id.nav_allperioicfaults);
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setMessage("גישה נדחתה");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });
        cardnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(":", Global.svar2);
                if(!(Global.svar2.equals("עובד אחזקה")))
                navController.navigate(R.id.nav_newuser);
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setMessage("גישה נדחתה");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }

        });
        carddeluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(Global.svar2.equals("עובד אחזקה")))
                    navController.navigate(R.id.nav_deluser);
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setMessage("גישה נדחתה");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });


        cardmyfaults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.myfaults);
            }
        });
        cardlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.mainActivity);
            }
        });

    }


    }






