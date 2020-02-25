package com.example.stenattadanceregister2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login2 extends AppCompatActivity {
    EditText et_1,et_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        et_1=findViewById(R.id.username);
        et_2=findViewById(R.id.pass);
    }

    public void login(View view)
    {
        String username = et_1.getText().toString();
        String password = et_2.getText().toString();
        if(username.equals("2") && password.equals("2")){
            Intent i=new Intent(this,PostingAttendance.class);
            startActivity(i);

        }   else{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

    }
}
