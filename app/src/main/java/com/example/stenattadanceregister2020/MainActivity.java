package com.example.stenattadanceregister2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity<netInfo> extends AppCompatActivity {




    ImageView imagelogo;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            //EventLogTags.Description.setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Please Check your Internetconnection")
                    .setPositiveButton("OK", null).show();
        } else {
            Toast.makeText(this,
                    "Iternet is Available", Toast.LENGTH_SHORT).show();
        }


    }

    public void next(View view) {
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }




}
