package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class Recycler extends AppCompatActivity {
    String [] title ,desc;
    MyAdapter myAdapter;
     RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recycler = findViewById(R.id.recyclerid);
        title = getResources().getStringArray(R.array.country_name);
        desc = getResources().getStringArray(R.array.country_desc);
        myAdapter=new MyAdapter(this,title,desc);
        recycler.setAdapter(myAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
