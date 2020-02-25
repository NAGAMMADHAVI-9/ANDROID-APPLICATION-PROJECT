package com.example.stenattadanceregister2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PostingAttendance extends AppCompatActivity implements MyAdapter.CallbackAttendance{
    private List<Attendance> attendanceList;

    TextView tv;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    Spinner sp_branch,sp_year,sp_section;
    List<Student> studentslist;

    DatabaseReference attendance_dr;

    int c_date,c_month,c_year;
    private Student student;
    String branch,year,section;
    int i;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_attendance);
        tv = findViewById(R.id.date);
        recyclerView = findViewById(R.id.recyclerview);
        database= FirebaseDatabase.getInstance();
        reference=database.getReference("Students");
        sp_branch = findViewById(R.id.branch);
        sp_year = findViewById(R.id.year);
        sp_section = findViewById(R.id.section);
        attendance_dr = database.getReference("Attendance");
        attendanceList=new ArrayList<>();
    }

    public void getdata(View view) {
        final ProgressDialog pd = new ProgressDialog(PostingAttendance.this);
        pd.setMessage("loading");
        pd.setMax(10);
        pd.show();

       String branch = sp_branch.getSelectedItem().toString();
        String year = sp_year.getSelectedItem().toString();
        String section = sp_section.getSelectedItem().toString();
        reference.child(branch).child(year).child(section).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentslist = new ArrayList<Student>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    student=postSnapshot.getValue(Student.class);
                    studentslist.add(student);
                    Attendance attendance=new Attendance();
                    attendance.setRollnumber(student.getRollno());
                    attendance.setName(student.getName());
                    attendance.setAttendance(false);
                    attendanceList.add(attendance);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
               MyAdapter adapter = new MyAdapter(getApplicationContext(),studentslist,PostingAttendance.this);
                recyclerView.setAdapter(adapter);
                pd.dismiss();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }



    public void selectDate(View view) {
        Calendar c = Calendar.getInstance();
        c_year = c.get(Calendar.YEAR);
        c_month = c.get(Calendar.MONTH);
        c_date = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //view.setMinDate(System.currentTimeMillis() - 1000);
                String mydate = dayOfMonth+"-"+(month+1)+"-"+year;
                tv.setText(mydate);
            }
        },c_year,c_month,c_date);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dialog.show();

    }

    public void submit(View view) {

        String branch = sp_branch.getSelectedItem().toString();
        String year = sp_year.getSelectedItem().toString();
        String section = sp_section.getSelectedItem().toString();
        String date = tv.getText().toString();
        if(date.isEmpty()){
            Toast.makeText(this,
                    "Please select the date", Toast.LENGTH_SHORT).show();
        }else{
            String datesplit[] = date.split("-");
            //String d = datesplit[2]+datesplit[1]+datesplit[0];
            String month="";
            switch (datesplit[1]){
                case "1":
                    month = "January";
                    break;
                case "2":
                    month = "February";
                    break;
                case "3":
                    month = "March";
                    break;
                case "4":
                    month = "April";
                    break;
                case "5":
                    month = "May";
                    break;
                case "6":
                    month = "June";
                    break;
                case "7":
                    month = "July";
                    break;
                case "8":
                    month = "August";
                    break;
                case "9":
                    month = "September";
                    break;
                case "10":
                    month = "October";
                    break;
                case "11":
                    month = "November";
                    break;
                case "12":
                    month = "December";
                    break;
            }
            for (i = 0; i < attendanceList.size(); i++) {
                attendance_dr.child(branch).child(year)
                        .child(section)
                        .child(datesplit[2])
                        .child(month)
                        .child(datesplit[0])
                        .push().setValue(attendanceList.get(i));
                Toast.makeText(PostingAttendance.this,
                        "Attendance posted successfullyy..",
                        Toast.LENGTH_SHORT).show();
            Attendance attendance = attendanceList.get(i);
            attendance.setAttendance(false);
                recyclerView.setAdapter(adapter);
                tv.setText("");
                /*startActivity(new Intent(PostingAttendance.this,PostingAttendance.class));*/
              /*  finish();*/
            }
        }

    }

    @Override
    public void getAttenance(int position, boolean attend) {
        Attendance attendance=attendanceList.get(position);
        attendance.setAttendance(attend);

        attendanceList.set(position,attendance);
    }
}
