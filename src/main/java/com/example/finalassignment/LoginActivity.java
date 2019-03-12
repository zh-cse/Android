package com.example.finalassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
    EditText edEmail ,edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail =(EditText)findViewById(R.id.edEmail);
        edPassword = (EditText)findViewById(R.id.edPassword);
    }

    public void LogIn(View view) {

        if(TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError("Email is required");
        }
        else if(TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError("Password is required");
        }
        else{
            ParseUser.logInInBackground(edEmail.getText().toString(), edPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser != null) {
                        Toast.makeText(LoginActivity.this, "Success ", Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(LoginActivity.this, BottomNavigation.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(LoginActivity.this, "Email or password is wrong", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void goSignUp(View view) {
        Intent intent =new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent);
        finish();
    }
}
