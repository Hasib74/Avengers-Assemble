package com.example.hasib.a2dcomicspuzzlegame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.RestrictTo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.MyBounceInterpolator;
import com.example.hasib.a2dcomicspuzzlegame.DatabaseBoos.GameDatabase;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;

public class Option extends AppCompatActivity {


     TranslateAnimation downtoupanimation;
     TranslateAnimation lefttorightanimation;
     TranslateAnimation righttoleft;
     TranslateAnimation uptodown;


    @Override
    protected void onResume() {
        super.onResume();
        downtoup();
        lefttoright();
        righttoleft();
        uptodown();

        startAnimation();

    }
    @Override
    protected void onStop() {
        super.onStop();
        sp = getSharedPreferences("Music", MODE_PRIVATE);
        if (sp.getBoolean("music", true)) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();


        sp = getSharedPreferences("Music",MODE_PRIVATE);
        if(sp.getBoolean("music",true))
        {
            mediaPlayer.start();
        }

        downtoup();
        lefttoright();
        righttoleft();
        uptodown();

        startAnimation();
    }


    @Override
    protected void onStart() {
        super.onStart();

        downtoup();
        lefttoright();
        righttoleft();
        uptodown();


        startAnimation();

    }

    Button back;
    Button result;

    ImageView sound,howtoplay;
    ImageView imageView;
    Button reset;
    Button balbtn;
    LinearLayout layout;
    GameDatabase gameDatabase;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_option);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        gameDatabase=new GameDatabase(getApplicationContext());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        result=(Button)findViewById(R.id.result);
        sound = (ImageView) findViewById(R.id.sounds);
        howtoplay = (ImageView) findViewById(R.id.howtoplay);
        reset=findViewById(R.id.reset);



        final Animation myAnim = AnimationUtils.loadAnimation(Option.this, R.anim.bounce);


        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
        myAnim.setInterpolator(interpolator);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset.startAnimation(myAnim);


                AlertDialog.Builder dialog = new AlertDialog.Builder(Option.this,R.style.MyDialogThem);
                dialog.setTitle("Reset Game");
                dialog.setMessage("Do you want to reset your game");
                dialog.setIcon(R.drawable.logo_avangers);
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameDatabase.removeData();

                        SharedPreferences sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);
                        SharedPreferences sp=getSharedPreferences("H",MODE_PRIVATE);
                        SharedPreferences  sm=getSharedPreferences("M",MODE_PRIVATE);

                        SharedPreferences.Editor shereMedium=sm.edit();
                        SharedPreferences.Editor sh=sp.edit();
                        SharedPreferences.Editor shereEasy=sharedPreferences.edit();


                        shereEasy.clear();
                        sh.clear();
                        shereMedium.clear();
                        shereEasy.commit();
                        sh.commit();
                        shereMedium.commit();
                   dialog.dismiss();
                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });

        downtoup();
        lefttoright();
        righttoleft();
        uptodown();

      //  startAnimation();










        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Option.this, ScoreActivity.class));



                result.startAnimation(myAnim);

               // finish();
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Option.this, SoundActivity.class));
               // finish();
                sound.startAnimation(myAnim);
            }
        });

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Option.this, HowtoworkActivity.class));
               // finish();
                howtoplay.startAnimation(myAnim);

            }
        });

       /* back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Option.this,MainActivity.class));

                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });*/

    }

    private void startAnimation() {
        imageView=findViewById(R.id.optionicon);

        layout=findViewById(R.id.optionlayout);

        imageView.setAnimation(uptodown);

        layout.setAnimation(downtoupanimation);





    }

 /*   @Override
    public void onBackPressed() {

        this.finish();
        startActivity(new Intent(Option.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }*/



    public  void uptodown() {
        uptodown=new TranslateAnimation(0,0,-400,0);
        uptodown.setDuration(400);
        uptodown.setFillAfter(true);
    }

    public  void righttoleft() {
        righttoleft=new TranslateAnimation(400,0,0,0);
        righttoleft.setDuration(400);
        righttoleft.setFillAfter(true);
    }

    public  void lefttoright() {
        lefttorightanimation=new TranslateAnimation(-400,0,0,0);
        lefttorightanimation.setDuration(400);
        lefttorightanimation.setFillAfter(true);
    }

    public  void downtoup() {
        downtoupanimation=new TranslateAnimation(0,0,1200,0);
        downtoupanimation.setDuration(400);
        downtoupanimation.setFillAfter(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}
