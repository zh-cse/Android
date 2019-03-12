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

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ProgrammingViewHolder> {

    private List<FirebaseAddDataModel> list;
    private Context context;

    public FirebaseAdapter(List<FirebaseAddDataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {


        FirebaseAddDataModel firebaseAddDataModel=list.get(position);
        holder.txtName.setText(firebaseAddDataModel.getName());
        holder.txtdes.setText(firebaseAddDataModel.getDesination());
        holder.txtteam.setText(firebaseAddDataModel.getTeam());
        Glide.with(context).load(firebaseAddDataModel.getImage()).into(holder.imgIcon);
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

