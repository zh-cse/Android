package com.example.assignment_android;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends Fragment {

      // Initialize Room database
    EmployeeDB db = Room.databaseBuilder(getApplicationContext(),
            EmployeeDB.class, "MyDB").allowMainThreadQueries().build();


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<EmployeeModel> employeeModels;
    private List<EmployeeModel> DbModels;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local, container, false);
        recyclerView = view.findViewById(R.id.programlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        employeeModels = new ArrayList<>();

        //Store data in shared Prefernce and Firebase
        Retrive_data_with_sharedPrefernce();


        DbModels = db.employeeDao().ShowAllEmployee();
        employeeModels.addAll(db.employeeDao().ShowAllEmployee());

        adapter = new ProgrammingAdapter(employeeModels,getContext());
        recyclerView.setAdapter(adapter);
        return view;

    }

    public void get_jsondata(){
        int id;
        String json;
        String name;
        String desination;
        String team;
        String image;
        try {
            InputStream inputStream = getActivity().getAssets().open("employeeList.json");
            int size = inputStream.available();
            byte [] buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                EmployeeModel person = new EmployeeModel();

                   //Show data directly from json file
//                person.setName(jsonObject.getString("name"));
//                person.setDesignation(jsonObject.getString("desegnation"));
//                person.setTeam(jsonObject.getString("team"));
//                person.setImage(jsonObject.getString("image"));


                //put data into variable

                name = jsonObject.getString("name");
                desination = jsonObject.getString("desegnation");
                team = jsonObject.getString("team");
                image = jsonObject.getString("image");

               //Set data for constructor
                person.setName(name);
                person.setDesignation(desination);
                person.setTeam(team);
                person.setImage(image);

                employeeModels.add(person);
            }

            // insert data into room
            db.employeeDao().addAllEmployee(employeeModels);

            store_data_with_sharedPrefernce();


        }
        catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    public LocalFragment() {
        // Required empty public constructor
    }

    // Store data
    public void store_data_with_sharedPrefernce(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Insert_status", Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean("Local_Status",true);
        editor.apply();

    }

    //Retrive Data from Shared Preference
    public void Retrive_data_with_sharedPrefernce(){

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Insert_status",Context.MODE_PRIVATE) ;
        Boolean restoreStatus = sharedPreferences.getBoolean("Local_Status",false);
        if(!restoreStatus){
            get_jsondata();
        }


    }

}
