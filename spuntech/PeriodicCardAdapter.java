package com.example.spuntech;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class PeriodicCardAdapter extends RecyclerView.Adapter {
    private List PeriodicFaultsList;
    private Context context;
    private List<PeriodicFault>  filtered;

    public PeriodicCardAdapter(Context context) {
        this.context = context;
    }

    public PeriodicCardAdapter(Context context,List PeriodicFaultsList) {
        this.PeriodicFaultsList = PeriodicFaultsList;
        this.context = context;
        this.filtered = new ArrayList<>();
        this.filtered.addAll(this.PeriodicFaultsList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.periodic_fault_table_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        final PeriodicCardAdapter.RowViewHolder rowViewHolder = (PeriodicCardAdapter.RowViewHolder) holder;
        final int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {


            rowViewHolder.txtUnit.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtFaulttype.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtHandler1.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNotify.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtPriority.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtUnit.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtUnit.setText("מכלול");
            rowViewHolder.txtFaulttype.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtFaulttype.setText("סוג תקלה");
            rowViewHolder.txtStatus.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtStatus.setText("סטאטוס עבודה");
            rowViewHolder.txtHandler1.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtHandler1.setText("מטפל בעבודה");
            rowViewHolder.txtNotify.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtNotify.setText("תזכורת");
            rowViewHolder.txtPriority.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtPriority.setText("דחיפות");
            rowViewHolder.txtDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtDate.setText("תאריך");


        } else {
            PeriodicFault PeriodicFault = (PeriodicFault) filtered.get(rowPos -1);


            rowViewHolder.txtUnit.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtFaulttype.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtHandler1.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNotify.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtPriority.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtUnit.setText(PeriodicFault.getUnit()+"");
            rowViewHolder.txtFaulttype.setText(PeriodicFault.getFaultType() + "");
            rowViewHolder.txtStatus.setText(PeriodicFault.getStatus() + "");
            rowViewHolder.txtHandler1.setText(PeriodicFault.getHandler1() + "");
            rowViewHolder.txtNotify.setText(PeriodicFault.getNotify() + "");
            rowViewHolder.txtPriority.setText(PeriodicFault.getPriority() + "");
            rowViewHolder.txtDate.setText(PeriodicFault.getDate() + "");




            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FullDetailsFault.class);
                    intent.putExtra("IDofFault",filtered.get(rowPos - 1).getId()+ "");
                    intent.putExtra("fromWhere","periodicFault");
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
                for (Object pr :  PeriodicFaultsList)
                {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                        filtered.add(periodicFault);
                }
                notifyDataSetChanged();

            }
        }).start();
    }

    public void filter(final String unit)
    {
                Log.d("hello", "run: " + unit);
                filtered.clear();
                for (Object pr :  PeriodicFaultsList)
                {
                    PeriodicFault periodicFault = (PeriodicFault) pr;
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()))
                    {
                        Log.d("hello", "run: " + unit);
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()))
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(unit.toLowerCase()))
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(unit.toLowerCase()))
                    {
                        filtered.add(periodicFault);
                    }
                    if(periodicFault.getHandler1().toLowerCase().equals(unit.toLowerCase()) ||
                            periodicFault.getHandler2().toLowerCase().equals(unit.toLowerCase())||
                            periodicFault.getHandler3().toLowerCase().equals(unit.toLowerCase()))
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getStatus().toLowerCase().equals(unit.toLowerCase()))
                    {
                        filtered.add(periodicFault);
                    }
                    if(periodicFault.getNotify().toLowerCase().equals(unit.toLowerCase()))
                    {
                        filtered.add(periodicFault);
                    }
                }
                notifyDataSetChanged();
            }

    public void filter(final String unit,final String subUnit) {

                filtered.clear();
                for (Object pr :  PeriodicFaultsList) {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) && (
                            periodicFault.getHandler1().toLowerCase().equals(subUnit.toLowerCase())||
                                    periodicFault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                                            periodicFault.getHandler3().toLowerCase().equals(subUnit.toLowerCase())))
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getNotify().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) && (
                            periodicFault.getHandler1().toLowerCase().equals(subUnit.toLowerCase())||
                                    periodicFault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                                    periodicFault.getHandler3().toLowerCase().equals(subUnit.toLowerCase())))
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getNotify().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(unit.toLowerCase()) && (
                            periodicFault.getHandler1().toLowerCase().equals(subUnit.toLowerCase()) ||
                                    periodicFault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                                    periodicFault.getHandler3().toLowerCase().equals(subUnit.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getNotify().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(unit.toLowerCase()) && (
                            periodicFault.getHandler1().toLowerCase().equals(subUnit.toLowerCase()) ||
                                    periodicFault.getHandler2().toLowerCase().equals(subUnit.toLowerCase())||
                                    periodicFault.getHandler3().toLowerCase().equals(subUnit.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getNotify().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (  (
                            periodicFault.getHandler1().toLowerCase().equals(unit.toLowerCase()) ||
                                    periodicFault.getHandler2().toLowerCase().equals(unit.toLowerCase())||
                                    periodicFault.getHandler3().toLowerCase().equals(unit.toLowerCase())) &&
                            periodicFault.getNotify().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (  (
                            periodicFault.getHandler1().toLowerCase().equals(unit.toLowerCase()) ||
                                    periodicFault.getHandler2().toLowerCase().equals(unit.toLowerCase())||
                                    periodicFault.getHandler3().toLowerCase().equals(unit.toLowerCase())) &&
                            periodicFault.getStatus().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getStatus().toLowerCase().equals(unit.toLowerCase()) &&
                            periodicFault.getNotify().toLowerCase().equals(subUnit.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }

                }
                notifyDataSetChanged();

            }

    public void filter(final String para1,final String para2,final String para3) {

                filtered.clear();
                for (Object pr :  PeriodicFaultsList)
                {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    ) {
                        filtered.add(periodicFault);
                        }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    //
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())

                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())

                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                        if (periodicFault.getTreatType().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(para1.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(para1.toLowerCase()) &&
                            periodicFault.getStatus().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                    if ((periodicFault.getHandler1().toLowerCase().equals(para1.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(para1.toLowerCase())
                                    ||  periodicFault.getHandler3().toLowerCase().equals(para1.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para3.toLowerCase())
                    ) {
                        filtered.add(periodicFault);
                    }
                }
                notifyDataSetChanged();

    }
    public void filter(final String para1, final String para2, final String para3, final String para4)
    {

                filtered.clear();
                for (Object pr :  PeriodicFaultsList)
                {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para4.toLowerCase())
                        )
                        {
                            filtered.add(periodicFault);
                        }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para4.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para4.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getTreatType().toLowerCase().equals(para1.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(para1.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para2.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para2.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para2.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(para3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(para3.toLowerCase())
                            ||  periodicFault.getHandler3().toLowerCase().equals(para3.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(para1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(para2.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(para3.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(para4.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                }

                notifyDataSetChanged();
    }

    public void filter(final String param1, final String param2, final String param3, final String param4,final String param5)
    {
                filtered.clear();
                for (Object pr :  PeriodicFaultsList) {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param5.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(param5.toLowerCase())
                                    || periodicFault.getHandler3().toLowerCase().equals(param5.toLowerCase()))
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                            &&  periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                            &&  periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            &&  periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            &&  periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase())
                            && periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            &&  periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            &&  periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(param1.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(param1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getFaultType().toLowerCase().equals(param1.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param2.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param3.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param3.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param5.toLowerCase())

                    )
                    {
                        filtered.add(periodicFault);
                    }
                }


                notifyDataSetChanged();

    }

    public void filter(final String param1, final String param2, final String param3, final String param4,final String param5,final String param6)
    {

                filtered.clear();
                for (Object pr :  PeriodicFaultsList) {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param4.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(param5.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(param5.toLowerCase())
                                    || periodicFault.getHandler3().toLowerCase().equals(param5.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param4.toLowerCase()) &&
                            (periodicFault.getHandler1().toLowerCase().equals(param5.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(param5.toLowerCase())
                                    || periodicFault.getHandler3().toLowerCase().equals(param5.toLowerCase()))
                            && periodicFault.getNotify().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param4.toLowerCase())
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                                    || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getSubUnit().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                    if (periodicFault.getSubUnit().toLowerCase().equals(param1.toLowerCase()) &&
                            periodicFault.getFaultType().toLowerCase().equals(param2.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(param3.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler2().toLowerCase().equals(param4.toLowerCase())
                            || periodicFault.getHandler3().toLowerCase().equals(param4.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(param5.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(param6.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }

                }

                notifyDataSetChanged();


    }
    public void filter(final String unit, final String subunit, final String faultType, final String treatType,final String handler,final String status,final String notify)
    {

                filtered.clear();
                for (Object pr :  PeriodicFaultsList) {
                    PeriodicFault periodicFault = (PeriodicFault)pr;
                    if (periodicFault.getUnit().toLowerCase().equals(unit.toLowerCase()) && periodicFault.getSubUnit().toLowerCase().equals(subunit.toLowerCase())
                            && periodicFault.getFaultType().toLowerCase().equals(faultType.toLowerCase())
                            && periodicFault.getTreatType().toLowerCase().equals(treatType.toLowerCase())
                            && (periodicFault.getHandler1().toLowerCase().equals(handler.toLowerCase())
                                    || periodicFault.getHandler2().toLowerCase().equals(handler.toLowerCase())
                                    || periodicFault.getHandler3().toLowerCase().equals(handler.toLowerCase()))
                            && periodicFault.getStatus().toLowerCase().equals(status.toLowerCase())
                            && periodicFault.getNotify().toLowerCase().equals(notify.toLowerCase())
                    )
                    {
                        filtered.add(periodicFault);
                    }
                }


                notifyDataSetChanged();

    }
   // @Override
    //public int getItemCount() {
   //     return PeriodicFaultsList.size() + 1;
    //}
    @Override
    public int getItemCount() {
        return filtered!= null? filtered.size()+1 : 0;
    }
    public static class RowViewHolder extends RecyclerView.ViewHolder {

        TextView txtUnit;
        TextView txtFaulttype;
        TextView txtDate;
        TextView txtHandler1;
        TextView txtStatus;
        TextView txtNotify;
        TextView txtPriority;
        Spinner spinnerUnit;
        Spinner spinnerSubUnit;
        Spinner spinnerfaultType;
        Spinner spinnertreatType;

        RowViewHolder(View itemView) {
            super(itemView);

            txtUnit = itemView.findViewById(R.id.periodic_fault_item_unit_txt);
            txtFaulttype = itemView.findViewById(R.id.periodic_fault_item_Faulttype_txt);
            txtStatus = itemView.findViewById(R.id.periodic_fault_item_status_txt);
            txtHandler1 = itemView.findViewById(R.id.periodic_fault_item_Handler1_txt);
            txtNotify = itemView.findViewById(R.id.periodic_fault_item_notify_txt);
            txtPriority = itemView.findViewById(R.id.periodic_fault_item_priority_txt);
            txtDate = itemView.findViewById(R.id.periodic_fault_item_date_txt);
            spinnerUnit = itemView.findViewById(R.id.sortByPeriodicUnit);
            spinnerSubUnit = itemView.findViewById(R.id.sortByPeriodicSubUnit);
            spinnerfaultType = itemView.findViewById(R.id.sortByPeriodicFaultType);
            spinnertreatType = itemView.findViewById(R.id.sortByPeriodicTreatTypeText);


        }
    }

}
