package com.example.spuntech;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter {
    private List faultsList;
    private Context context;
    private List<Fault>  filtered;
    public CardAdapter(Context context,List faultsList) {
        this.faultsList = faultsList;
        this.context = context;
        this.filtered = new ArrayList<>();
        this.filtered.addAll(this.faultsList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.fault_table_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        final RowViewHolder rowViewHolder = (RowViewHolder) holder;

        final int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {


            rowViewHolder.txtUnit.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTreattypr.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtHandler.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtPriority.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtUnit.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtUnit.setText("מכלול");
            rowViewHolder.txtTreattypr.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtTreattypr.setText("סוג תקלה");
            rowViewHolder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtStatus.setText("סטאטוס עבודה");
            rowViewHolder.txtHandler.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtHandler.setText("מטפל בעבודה");
            rowViewHolder.txtDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtDate.setText("תאריך");
            rowViewHolder.txtPriority.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtPriority.setText("דחיפות");

        } else {
            Fault fault = (Fault) filtered.get(rowPos - 1);


            rowViewHolder.txtUnit.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTreattypr.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtHandler.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtPriority.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtUnit.setText(fault.getUnit()+"");
            rowViewHolder.txtTreattypr.setText(fault.getTreatType() + "");
            rowViewHolder.txtStatus.setText(fault.getStatus() + "");
            rowViewHolder.txtDate.setText(fault.getDate() + "");
            rowViewHolder.txtHandler.setText(fault.getHandler1() + "");
            rowViewHolder.txtPriority.setText(fault.getPriority() + "");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FullDetailsFault.class);
                    intent.putExtra("IDofFault",((Fault) filtered.get(rowPos - 1)).getId() + "");
                    intent.putExtra("fromWhere","fault");
                    context.startActivity(intent);
                }
            });
        }
    }

    public void filter()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                filtered.clear();
                for (Object pr :  faultsList)
                {
                    Fault Fault = (Fault)pr;
                    filtered.add(Fault);
                }
                notifyDataSetChanged();

            }
        }).start();
    }

    public void filter(final String unit)
    {
        Log.d("hello", "run: " + unit);
        filtered.clear();
        for (Object pr :  faultsList)
        {
            Fault Fault = (Fault) pr;
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()))
            {
                Log.d("hello", "run: " + unit);
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()))
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(unit.toLowerCase()))
            {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(unit.toLowerCase()))
            {
                filtered.add(Fault);
            }
            if(Fault.getHandler1().toLowerCase().equals(unit.toLowerCase()) ||
                    Fault.getHandler2().toLowerCase().equals(unit.toLowerCase())||
                    Fault.getHandler3().toLowerCase().equals(unit.toLowerCase()))
            {
                filtered.add(Fault);
            }
            if (Fault.getStatus().toLowerCase().equals(unit.toLowerCase()))
            {
                filtered.add(Fault);
            }
            if(Fault.getPriority().toLowerCase().equals(unit.toLowerCase()))
            {
                filtered.add(Fault);
            }
        }
        notifyDataSetChanged();
    }

    public void filter(final String unit,final String subUnit) {

        filtered.clear();
        for (Object pr :  faultsList) {
            Fault Fault = (Fault)pr;
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) && (
                    Fault.getHandler1().toLowerCase().equals(subUnit.toLowerCase())||
                            Fault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                            Fault.getHandler3().toLowerCase().equals(subUnit.toLowerCase())))
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getPriority().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) && (
                    Fault.getHandler1().toLowerCase().equals(subUnit.toLowerCase())||
                            Fault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                            Fault.getHandler3().toLowerCase().equals(subUnit.toLowerCase())))
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getPriority().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(unit.toLowerCase()) && (
                    Fault.getHandler1().toLowerCase().equals(subUnit.toLowerCase()) ||
                            Fault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                            Fault.getHandler3().toLowerCase().equals(subUnit.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getPriority().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(unit.toLowerCase()) && (
                    Fault.getHandler1().toLowerCase().equals(subUnit.toLowerCase()) ||
                            Fault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                            Fault.getHandler3().toLowerCase().equals(subUnit.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getPriority().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (  (
                    Fault.getHandler1().toLowerCase().equals(unit.toLowerCase()) ||
                            Fault.getHandler2().toLowerCase().equals(unit.toLowerCase())||
                            Fault.getHandler3().toLowerCase().equals(unit.toLowerCase())) &&
                    Fault.getPriority().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (  (
                    Fault.getHandler1().toLowerCase().equals(unit.toLowerCase()) ||
                            Fault.getHandler2().toLowerCase().equals(unit.toLowerCase())||
                            Fault.getHandler3().toLowerCase().equals(unit.toLowerCase())) &&
                    Fault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getStatus().toLowerCase().equals(unit.toLowerCase()) &&
                    Fault.getPriority().toLowerCase().equals(subUnit.toLowerCase())
            )
            {
                filtered.add(Fault);
            }

        }
        notifyDataSetChanged();

    }

    public void filter(final String para1,final String para2,final String para3) {

        filtered.clear();
        for (Object pr :  faultsList)
        {
            Fault Fault = (Fault)pr;
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            //
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())

            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())

            ) {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
            ) {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(para1.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(para1.toLowerCase()) &&
                    Fault.getStatus().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
            if ((Fault.getHandler1().toLowerCase().equals(para1.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para1.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para1.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para3.toLowerCase())
            ) {
                filtered.add(Fault);
            }
        }
        notifyDataSetChanged();

    }
    public void filter(final String para1, final String para2, final String para3, final String para4)
    {

        filtered.clear();
        for (Object pr :  faultsList)
        {
            Fault Fault = (Fault)pr;
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getTreatType().toLowerCase().equals(para1.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                    ||  Fault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(para1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(para4.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
        }

        notifyDataSetChanged();
    }

    public void filter(final String param1, final String param2, final String param3, final String param4,final String param5)
    {
        filtered.clear();
        for (Object pr :  faultsList) {
            Fault Fault = (Fault)pr;
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param5.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param5.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param5.toLowerCase()))
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                    &&  Fault.getStatus().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                    &&  Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    &&  Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    &&  Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase())
                    && Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    &&  Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    &&  Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(param1.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(param1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
            if (Fault.getFaultType().toLowerCase().equals(param1.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param2.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param5.toLowerCase())

            )
            {
                filtered.add(Fault);
            }
        }


        notifyDataSetChanged();

    }

    public void filter(final String param1, final String param2, final String param3, final String param4,final String param5,final String param6)
    {

        filtered.clear();
        for (Object pr :  faultsList) {
            Fault Fault = (Fault)pr;
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param4.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(param5.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(param5.toLowerCase())
                            || Fault.getHandler3().toLowerCase().equals(param5.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param4.toLowerCase()) &&
                    (Fault.getHandler1().toLowerCase().equals(param5.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(param5.toLowerCase())
                            || Fault.getHandler3().toLowerCase().equals(param5.toLowerCase()))
                    && Fault.getPriority().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }
            if (Fault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                    Fault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                    && Fault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                    && (Fault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                    || Fault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                    && Fault.getStatus().toLowerCase().equals(param5.toLowerCase())
                    && Fault.getPriority().toLowerCase().equals(param6.toLowerCase())
            )
            {
                filtered.add(Fault);
            }

        }

        notifyDataSetChanged();


    }
    public void filter(final String unit, final String subunit, final String faultType, final String treatType,final String handler,final String status,final String Priority) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                filtered.clear();
                for (Object pr : faultsList) {
                    Fault Fault = (Fault) pr;
                    if (Fault.getUnit().toLowerCase().equals(unit.toLowerCase()) && Fault.getSubUnit().toLowerCase().equals(subunit.toLowerCase())
                            && Fault.getFaultType().toLowerCase().equals(faultType.toLowerCase())
                            && Fault.getTreatType().toLowerCase().equals(treatType.toLowerCase())
                            && (Fault.getHandler1().toLowerCase().equals(handler.toLowerCase())
                            || Fault.getHandler2().toLowerCase().equals(handler.toLowerCase())
                            || Fault.getHandler3().toLowerCase().equals(handler.toLowerCase()))
                            && Fault.getStatus().toLowerCase().equals(status.toLowerCase())
                            && Fault.getPriority().toLowerCase().equals(Priority.toLowerCase())
                    ) {
                        filtered.add(Fault);
                    }
                }


                notifyDataSetChanged();

            }
        }).start();
    }
    @Override
    public int getItemCount() {
        return filtered!= null? filtered.size()+1 : 0;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        TextView txtUnit;
        TextView txtTreattypr;
        TextView txtStatus;
        TextView txtDate;
        TextView txtHandler;
        TextView txtPriority;

        RowViewHolder(View itemView) {
            super(itemView);

            txtUnit = itemView.findViewById(R.id.txtUnit);
            txtTreattypr = itemView.findViewById(R.id.txttreattype);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtHandler = itemView.findViewById(R.id.txtHandler);
            txtPriority = itemView.findViewById(R.id.txtPriority);
        }
    }

}
