package com.example.assignment_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ArrayList<String> arrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        final LocalFragment localFragment=new LocalFragment();
        final MoreFragment moreFragment=new MoreFragment();
        final ServerFragment serverFragment=new ServerFragment();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if(id==R.id.local){
                    setFragment(localFragment);
                    return true;
                }else if(id==R.id.server){
                    setFragment(serverFragment);
                    return true;
                }else if(id==R.id.more){
                    setFragment(moreFragment);
                    return true;
                }
                return false;
            }
        });
        navigationView.setSelectedItemId(R.id.local);
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    public void logout(View view){
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        ParseUser.logOut();
        Intent intent=new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        progressDialog.dismiss();
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Want To Exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
