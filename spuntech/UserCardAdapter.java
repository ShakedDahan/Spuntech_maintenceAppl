package com.example.spuntech;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.userHolder> {
    private Context context;
    private  ArrayList<User> Users;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("users");
    public UserCardAdapter(Context context, ArrayList<User> Users) {
        this.context = context;
        this.Users = Users; }
    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.userscard, parent, false);
        return new userHolder(view); }
    @Override
    public void onBindViewHolder(@NonNull final userHolder holder, int position) {
        final User user = Users.get(position);
        holder.setDetails(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("מחיקת משתמש")
                .setMessage("האם אתה בטוח?")
                .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Users.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), Users.size());
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                    if(holder.txtName.getText().toString().equals(ds.child("username").getValue().toString()))
                                    ds.getRef().removeValue(); } }
                            @Override
                            public void onCancelled(DatabaseError error) {
                                Log.w("TAG", "Failed to read value.", error.toException());  // Failed to read value
                            }
                        });

                    }

                })

                .setNegativeButton("לא", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    class userHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtDistance, txtGravity, txtDiameter;

        userHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.text_title);
            txtDistance = itemView.findViewById(R.id.text_title2);
            txtGravity = itemView.findViewById(R.id.text_title1);
            txtDiameter = itemView.findViewById(R.id.text_title3);
        }

        void setDetails(User user) {
            txtName.setText(txtName.getText()+user.getUsername());
            txtDistance.setText(txtDistance.getText()+user.getFirstname());
            txtGravity.setText( txtGravity.getText()+user.getLastname());
            txtDiameter.setText(  txtDiameter.getText()+user.getPass());
        }
    }

}
