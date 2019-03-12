package com.example.finalassignment;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                goMainActivity();
            }
        });
        thread.start();
    }
    public void goMainActivity(){

        try{
            Thread.sleep(3000);
            Intent intent = new Intent(SplashScreen.this, SignUp.class);
            startActivity(intent);
            finish();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }


    }
}
