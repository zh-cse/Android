package com.example.assignment_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private List<EmployeeModel> list;
    private Context context;

    public ProgrammingAdapter(List<EmployeeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {


        EmployeeModel employeeModel =list.get(position);
        holder.txtName.setText(employeeModel.getName());
        holder.txtdes.setText(employeeModel.getDesignation());
        holder.txtteam.setText(employeeModel.getTeam());
        Glide.with(context).load(employeeModel.getImage()).into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        // return data.length;
        return list.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgIcon;
        public TextView txtName,txtdes,txtteam;
        public ProgrammingViewHolder(View itemView) {
            super(itemView);
            imgIcon=itemView.findViewById(R.id.imgIcon);
            txtName=itemView.findViewById(R.id.txtName);
            txtdes=itemView.findViewById(R.id.txtdesig);
            txtteam=itemView.findViewById(R.id.txtteam);
        }
    }
}
