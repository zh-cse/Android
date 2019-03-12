package com.example.assignment_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends Activity {
    private ProgressBar progressBar;
    private int setProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        Check database insert
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        progressBar=(ProgressBar) findViewById(R.id.progressbarid);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();
    }
    public void doWork(){
//        for (setProgress=20;setProgress<=100;setProgress=setProgress+20){
            try {
                Thread.sleep(1000);
//                progressBar.setProgress(setProgress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

//    }
    public void startApp(){
        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
