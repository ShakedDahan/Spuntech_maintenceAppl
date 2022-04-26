package com.example.spuntech;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class EmployeeByNameCountAdapter extends RecyclerView.Adapter{
    private List myemployeestat;
    private Context context;

    public EmployeeByNameCountAdapter(Context context,List myemployeestat) {
        this.myemployeestat = myemployeestat;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.employee_table_list_stats, parent, false);

        return new EmployeeByNameCountAdapter.RowViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        EmployeeByNameCountAdapter.RowViewHolder rowViewHolder = (EmployeeByNameCountAdapter.RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtMonth.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtOpen.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtClose.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtMonth.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtMonth.setText("עובד");
            rowViewHolder.txtOpen.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtOpen.setText("כמות");
            rowViewHolder.txtClose.setTextColor(context.getResources().getColor(R.color.colorWhite));
            rowViewHolder.txtClose.setText("פתוחות");

        } else {
            myEmployeeStats myEmployeeStats = (myEmployeeStats) myemployeestat.get(rowPos - 1);

            rowViewHolder.txtMonth.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtOpen.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtClose.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtMonth.setText(myEmployeeStats.getemployee() + "");
            rowViewHolder.txtOpen.setText(myEmployeeStats.getCount()+"");
            rowViewHolder.txtClose.setText(myEmployeeStats.getOpen()+"");

        }
    }

    @Override
    public int getItemCount() {
        return myemployeestat.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtMonth;
        TextView txtOpen;
        TextView txtClose;


        RowViewHolder(View itemView) {
            super(itemView);
            txtMonth = itemView.findViewById(R.id.employeestatsname);
            txtOpen = itemView.findViewById(R.id.employeestatOpen);
            txtClose = itemView.findViewById(R.id.employeestatClose);

        }
    }
}
