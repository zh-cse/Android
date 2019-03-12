package com.example.finalassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    Context context;
    String [] title,desc;
    public MyAdapter(Context context, String[] title, String[] desc) {
        this.context = context;
        this.title = title;
        this.desc = desc;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.titletextview.setText(title[position]);
        holder.desctextview.setText(desc[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class myViewHolder extends  RecyclerView.ViewHolder{
       TextView  titletextview;
        TextView desctextview;

        public  myViewHolder(@NonNull View itemView){
            super(itemView);
            titletextview = itemView.findViewById(R.id.titletextview);
            desctextview = itemView.findViewById(R.id.description);

        }

    }
}
