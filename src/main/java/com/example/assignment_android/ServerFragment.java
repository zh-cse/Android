package com.example.assignment_android;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**public class
 * A simple {@link Fragment} subclass.
 */
public class ServerFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<EmployeeModel> employeeModels;
    private ArrayList<FirebaseAddDataModel> firebaseAddDataModels;


    DatabaseReference databaseReference;

    public ServerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_server, container, false);
        recyclerView = view.findViewById(R.id.programlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("Zahid");



        // Shared Refference
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Insert_status1",Context.MODE_PRIVATE) ;
        Boolean restoreStatus = sharedPreferences.getBoolean("status1",false);
        if(!restoreStatus){
            new ServerAsyncTask().execute();
        }



        // Show data from database
        databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                FirebaseAddDataModel firebaseAddDataModel=dataSnapshot1.getValue(FirebaseAddDataModel.class);
                firebaseAddDataModels.add(firebaseAddDataModel);
            }
            adapter= new FirebaseAdapter(firebaseAddDataModels,getContext());
            recyclerView.setAdapter(adapter);
            }

           @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"No data found",Toast.LENGTH_SHORT).show();

            }
        });

        employeeModels = new ArrayList<>();
        firebaseAddDataModels = new ArrayList<>();

        adapter = new ProgrammingAdapter(employeeModels,getContext());
        recyclerView.setAdapter(adapter);
        return view;

    }


        public class  ServerAsyncTask extends AsyncTask<Void ,Void,Void> {

            DatabaseReference databaseReference;
         //Default Constructor
            public ServerAsyncTask(){

            }

           @Override
            protected Void doInBackground(Void... voids) {

                databaseReference = FirebaseDatabase.getInstance().getReference("Zahid");

                String Id;
                String Name;
                String Desination;
                String Team;
                String Image;
                String json;

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

                        Id = databaseReference.push().getKey();
                        Name= jsonObject.getString("name");
                        Desination = jsonObject.getString("desegnation");
                        Team =jsonObject.getString("team");
                        Image = jsonObject.getString("image");

                        // Add to database
                        FirebaseAddDataModel firebaseAddDataModel = new FirebaseAddDataModel(Id,Name,Desination,Team,Image);
                        //Saving the Artist
                        databaseReference.child(Id).setValue(firebaseAddDataModel);
                    }



                }
                catch (IOException e){
                    e.printStackTrace();
                }
                catch (JSONException e){
                e.printStackTrace();
                }
            return null;
        }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Insert_status1",Context.MODE_PRIVATE) ;
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putBoolean("status1",true);
                editor.commit();

            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
    }

}
