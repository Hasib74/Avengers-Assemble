package com.example.hasib.a2dcomicspuzzlegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.MyBounceInterpolator;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class about extends Activity {
  //  Button back_about_button, go_to_game_button;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CardView cardView=findViewById(R.id.cardView);


        final Animation myAnim = AnimationUtils.loadAnimation(about.this, R.anim.activity_animation);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(4, 4);

        myAnim.setInterpolator(interpolator);
        //overridePendingTransition(R.anim.enter, R.anim.exit);

        cardView.startAnimation(myAnim);


    /*    back_about_button = findViewById(R.id.back_about);
        go_to_game_button = findViewById(R.id.go_game_about);*/


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //this.finish();
      //  finish();
        //startActivity(new Intent(about.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}
