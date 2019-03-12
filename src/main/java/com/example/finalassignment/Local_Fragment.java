package com.example.finalassignment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Local_Fragment extends Fragment {
    String [] title, desc;
    MyAdapter myAdapter;
    RecyclerView recycler;

    public Local_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local_, container,false);
        recycler = view.findViewById(R.id.recyclerid);
        title = getResources().getStringArray(R.array.country_name);
        desc = getResources().getStringArray(R.array.country_desc);
        myAdapter=new MyAdapter(getContext(),title,desc);
        recycler.setAdapter(myAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
