package com.example.finalassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static android.app.ProgressDialog.show;

public class SignUp extends Activity {
    EditText edEmail ,edPassword ,edName, edconPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edEmail =(EditText)findViewById(R.id.edEmail);
        edPassword = (EditText)findViewById(R.id.edPassword);
        edName =(EditText)findViewById(R.id.edName);
        edconPassword = (EditText)findViewById(R.id.edconPassword);
    }

    public void SignUp(View view) {
        if(TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError("Email is required");
        }
        else if(TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError("Password is required");
        }
        else if(TextUtils.isEmpty(edName.getText())){
            edName.setError("Password is required");
        }
        else if(TextUtils.isEmpty(edconPassword.getText())){
            edconPassword.setError("Password is required");
        }
        else if(!edPassword.getText().toString().equals(edconPassword.getText().toString())){
            Toast.makeText(SignUp.this,"Password not Matched", Toast.LENGTH_LONG).show();

        }
        else{
            ParseUser user = new ParseUser();
          // Set the user's username and password, which can be obtained by a forms
            user.setUsername(edName.getText().toString().trim());
            user.setEmail(edEmail.getText().toString().trim());
            user.setPassword(edPassword.getText().toString().trim());
            user.put("name",edName.getText().toString().trim());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(SignUp.this,"Welcome", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUp.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void gosignin(View view) {
        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
