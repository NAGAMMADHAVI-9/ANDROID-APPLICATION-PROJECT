package com.example.stenattadanceregister2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUpload extends AppCompatActivity {
    EditText et_rollno, et_name, et_mobileno, et_email;
    Spinner sp_branch, sp_year, sp_section;

    LocationManager manager;

    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase database;
    DatabaseReference reference;
    List<Student> studentslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_upload);
        et_rollno = findViewById(R.id.studentrollno);
        et_name = findViewById(R.id.studentname);
        et_mobileno = findViewById(R.id.studentmobileno);
        et_email = findViewById(R.id.email);
        sp_branch = findViewById(R.id.branch);
        sp_year = findViewById(R.id.year);
        sp_section = findViewById(R.id.section);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Students");




    }

    public void submit(View view) {
        final String rollno = et_rollno.getText().toString();
        final String name = et_name.getText().toString();
        final String mobileno = et_mobileno.getText().toString();
        final String email = et_email.getText().toString();
        final String branch = sp_branch.getSelectedItem().toString();
        final String year = sp_year.getSelectedItem().toString();
        final String section = sp_section.getSelectedItem().toString();
       // Toast.makeText(this, ""+branch+year+section, Toast.LENGTH_SHORT).show();

      /* reference.child(branch).child(year).child(section).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                   studentslist.add(snapshot.getValue(Student.class));
               }
               Toast.makeText(StudentDataUpload.this,
                       ""+studentslist.size(), Toast.LENGTH_SHORT).show();
               if(studentslist.size()!=0){
                   int count = 0;
                   for(int i = 0;i<studentslist.size();i++){
                       Toast.makeText(StudentDataUpload.this, ""+studentslist.get(i).getRollno(), Toast.LENGTH_SHORT).show();
                      *//* if (rollno.equals(studentslist.get(i).getRollno())) {
                           Toast.makeText(StudentDataUpload.this,
                                   "Already Exist", Toast.LENGTH_SHORT).show();
                       }else{
                           count++;
                           if (count == studentslist.size()) {
                               Toast.makeText(StudentDataUpload.this,
                                       "Data is Not Available", Toast.LENGTH_SHORT).show();
                           }*//**//*
                       }*//*
                   }

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       })*/;



        if (rollno.isEmpty() || name.isEmpty() || mobileno.isEmpty() || email.isEmpty() || branch.isEmpty() || year.isEmpty() ||
                section.isEmpty()) {
            Toast.makeText(this, "Please fill the missing fields", Toast.LENGTH_SHORT).show();
        } else {
            if (rollno.isEmpty() || name.isEmpty() || email.isEmpty() || branch.isEmpty() || year.isEmpty() || section.isEmpty()) {

            } else {
                Student s = new Student(rollno, name, mobileno, email, branch, year, section);
                reference.child(branch).child(year)
                        .child(section).push().setValue(s)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(StudentDataUpload.this,
                                        "Saved Successfully...", Toast.LENGTH_SHORT).show();
                                et_rollno.setText("");
                                et_name.setText("");
                                et_mobileno.setText("");
                                et_email.setText("");

                            }
                        });

            }
        }
    }
}