package com.example.spuntech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by User on 2/12/2018.
 */

public class ScrollViewAdapter extends RecyclerView.Adapter<ScrollViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<UserView> mNames = new ArrayList<UserView>();
    private Context mContext;

    public ScrollViewAdapter(Context context, ArrayList<UserView> userViews) {
        mNames = userViews;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollviewcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.amount.setText(mNames.get(position).getA());
            holder.name.setText(mNames.get(position).getB());
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView amount;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount_fault);
            name = itemView.findViewById(R.id.name);
        }
    }
}