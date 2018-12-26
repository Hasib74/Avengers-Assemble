package com.example.hasib.a2dcomicspuzzlegame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.MyBounceInterpolator;
import com.example.hasib.a2dcomicspuzzlegame.Model.RevealAnimation;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;

/**
 *Created by HASIB on 8/12/2018.
 */

public class ModeActivity extends AppCompatActivity {
    TranslateAnimation downtoupanimation;
    TranslateAnimation lefttorightanimation;
    TranslateAnimation righttoleft;
    TranslateAnimation uptodown;
    int a=0;
    @Override
    protected void onResume() {
        super.onResume();
        downtoup();
        lefttoright();
        righttoleft();
        uptodown();
        setAnimation();
/*
        if (!Variable.mediaPlayer.isPlaying() ){
            Variable.mediaPlayer.start();
        }*/

    }




    @Override
    protected void onRestart() {
        super.onRestart();
        downtoup();
        lefttoright();
        righttoleft();
        uptodown();

        setAnimation();


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.pause();
        }
    }
/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case android.R.id.home:
                if (Common.isPlaying){
                    Variable.mediaPlayer.pause();
                    Common.isPlaying=false;
                    a=1;
                }
                *//*dialog("This is The Home Case");
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
*//*
                break;
        }
        return false;
    }*/






    @Override
    protected void onStart() {
        super.onStart();

      /*  if (!Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.start();
        }*/

        downtoup();
        lefttoright();
        righttoleft();
        uptodown();
        setAnimation();


        /*if (!Common.isPlaying){
            Variable.mediaPlayer.start();
            Variable.mediaPlayer.setLooping(true);
            Common.isPlaying=true;

        }else {

        }*/
    }


   /* @Override
    protected void onPause() {

        super.onPause();
        if (Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.pause();
        }
    }
*/
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (!Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.start();
        }

        final ImageView easy= (ImageView) findViewById(R.id.easybtn);
        final ImageView medium= (ImageView) findViewById(R.id.mediumbtn);
        final ImageView hard= (ImageView) findViewById(R.id.hardbtn);

    //    getActionBar().setHomeButtonEnabled(true);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        downtoup();
        lefttoright();
        righttoleft();
        uptodown();


        setAnimation();



        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(ModeActivity.this, R.anim.bounce);

                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);

                easy.startAnimation(myAnim);

                Intent intent =new Intent(ModeActivity.this,EasyClass.class);
              //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(ModeActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);

                medium.startAnimation(myAnim);
              //  setContentView(R.layout.medium_activity);

                Intent intent=new Intent(ModeActivity.this,MediumClass.class);

               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

              /*  new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        music_method();
                        Intent in=new Intent(ModeActivity.this,MediumClass.class);
                        startActivity(in);
               //         finish();
                    }
                },500);
                setContentView(R.layout.activity_mode);
*/
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(ModeActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);

                Intent intent=new Intent(ModeActivity.this,HardClass.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);



                startActivity(intent);

                hard.startAnimation(myAnim);
                overridePendingTransition(R.anim.enter, R.anim.exit);

         /*       setContentView(R.layout.hard_activity);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music_method();
                        Intent in=new Intent(ModeActivity.this,HardClass.class);
                        startActivity(in);
                 //       finish();
                    }
                },500);

                setContentView(R.layout.activity_mode);*/

            }
        });

    }

    private void setAnimation() {
        LinearLayout linearLayout=findViewById(R.id.layout);
        ImageView icon=findViewById(R.id.icon);
        linearLayout.setAnimation(downtoupanimation);
        icon.setAnimation(uptodown);
    }


    private void righttoleft() {
        righttoleft=new TranslateAnimation(2000,0,0,0);
        righttoleft.setDuration(400);
        righttoleft.setFillAfter(true);
    }

    private void lefttoright() {
        lefttorightanimation=new TranslateAnimation(-2000,0,0,0);
        lefttorightanimation.setDuration(400);
        lefttorightanimation.setFillAfter(true);
    }

    private void downtoup() {
        downtoupanimation=new TranslateAnimation(0,0,2000,0);
        downtoupanimation.setDuration(400);
        downtoupanimation.setFillAfter(true);
    }


    private void uptodown() {
        uptodown=new TranslateAnimation(0,0,-2000,0);
        uptodown.setDuration(400);
        uptodown.setFillAfter(true);
    }





   /* @Override
    public void onBackPressed() {


       tonBackPressed();
        //startActivity(new Intent(ModeActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);


        ///super.onBackPressed();

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }

    private void startRevealActivity(View v,Class<EasyClass> easyClassClass) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this,easyClassClass );
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }
}
