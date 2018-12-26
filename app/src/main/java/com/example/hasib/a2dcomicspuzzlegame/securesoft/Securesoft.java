package com.example.hasib.a2dcomicspuzzlegame.securesoft;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.hasib.a2dcomicspuzzlegame.R;
import com.example.hasib.a2dcomicspuzzlegame.Splashscreen;

public class Securesoft extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securesoft_logo_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                finish();
                startActivity(new Intent(Securesoft.this, Splashscreen.class));


            }



        },1400);
    }
}
