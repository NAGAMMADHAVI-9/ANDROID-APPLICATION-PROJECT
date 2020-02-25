package com.example.stenattadanceregister2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Button bt_1,bt_2,bt_3;
    EditText et_1,et_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt_1=findViewById(R.id.button1);
        bt_2=findViewById(R.id.button2);
        bt_3=findViewById(R.id.button3);

    }


    public void nextpg1(View view)  {

        Intent i = new Intent(this, LoginPage.class);
        startActivity(i);

    }

    public void nextpg2(View view) {
        Intent i = new Intent(this,Login2.class);
        startActivity(i);

    }

    public void gotoAttendanceReport(View view) {
        Intent i=new Intent(this,AttendanceReportActivity.class);
        startActivity(i);
    }
}
