package com.example.assignment_android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    EditText editEmail,editPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail=findViewById(R.id.editEmail);
        editPass=findViewById(R.id.editPass);
        if(ParseUser.getCurrentUser()!=null){
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();

        }
    }
    public void login(View view){
        if( TextUtils.isEmpty(editEmail.getText())){

            editEmail.setError( "Email is required!" );
        }else if( TextUtils.isEmpty(editPass.getText())){

            editPass.setError( "Password is required!" );
        }else {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            ParseUser.logInInBackground(editEmail.getText().toString(),editPass.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    progressDialog.dismiss();
                    if (parseUser != null) {
                        Toast.makeText(MainActivity.this, "Welocome", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void signup (View view){
        Intent intent=new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    public void backsignup (View view){
        Intent intent=new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    public void forgot (View view){
        Intent intent=new Intent(MainActivity.this,ResetPasswordActivity.class);
        startActivity(intent);
    }
    public void sign_in(View view) {
        Intent intent=new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }

}
