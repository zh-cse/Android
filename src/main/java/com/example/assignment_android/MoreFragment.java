package com.example.assignment_android;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    DatabaseReference databaseReference;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        databaseReference = FirebaseDatabase.getInstance().getReference("mydb");
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

}
