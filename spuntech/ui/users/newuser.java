package com.example.spuntech.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.spuntech.R;
import com.example.spuntech.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newuser extends Fragment {
    private Button bt;
    private EditText etuser;
    private EditText etpass;
    private EditText etfirstname;
    private EditText etlastname;
    private EditText etphone;
    private EditText etemail;
    private newusersviewmodel galleryViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(newusersviewmodel.class);
        View root = inflater.inflate(R.layout.fragment_newuser, container, false);
        bt=(Button)root.findViewById(R.id.signUpNewUserButton) ;
        etuser = (EditText)root.findViewById(R.id.Newusername);
        etpass = (EditText)root.findViewById(R.id.Newpassword);
        etfirstname = (EditText)root.findViewById(R.id.firstname);
        etlastname = (EditText)root.findViewById(R.id.lastname);
        etemail = (EditText)root.findViewById(R.id.mail);
        etphone = (EditText)root.findViewById(R.id.newUserPhone);
        final Spinner spinner = (Spinner) root.findViewById(R.id.level);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinneritems, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)parent.getChildAt(0);
                tv.setTextColor(getResources().getColor(R.color.colorBlue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                User user= new User(etuser.getText().toString(),etfirstname.getText().toString(),etlastname.getText().toString(),etpass.getText().toString(),
                        etphone.getText().toString(),etemail.getText().toString(),spinner.getSelectedItem().toString());

                myRef.child("users").push().setValue(user);
<<<<<<< HEAD
                
=======
>>>>>>> parent of 5eaeb62... worked on the PeriodicSinonFragment and allfaults and newfault, HomeFragment
            }
        });

        return root;
    }
<<<<<<< HEAD
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        final NavController navController = Navigation.findNavController(view);


    }
=======
>>>>>>> parent of 5eaeb62... worked on the PeriodicSinonFragment and allfaults and newfault, HomeFragment
}
