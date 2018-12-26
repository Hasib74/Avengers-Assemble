package com.example.hasib.a2dcomicspuzzlegame;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;

public class MediumClass extends AppCompatActivity {

    ImageView img1,img2,img3,img4,img5,img6;
    ImageView lock1,lock2,lock3,lock4,lock5;

    TranslateAnimation downtoupanimation;
    TranslateAnimation lefttorightanimation;
    TranslateAnimation righttoleft;
    TranslateAnimation uptodown;

    //boolean gameSolve=false;

    public   boolean solveGame;

    int antman=R.drawable.antman;
    int doctoreStrane=R.drawable.doctorstrange;
    int falson=R.drawable.falson;
    int vison=R.drawable.vison;
    int warmansing=R.drawable.warmasigin;
    int winter_soldier=R.drawable.winter_soldier;


  //  SharedPreferences sharedPreferences;


    int[] image1={R.drawable.ant1,R.drawable.ant5,R.drawable.ant9,R.drawable.ant13,R.drawable.ant2,R.drawable.ant6,R.drawable.ant10,R.drawable.ant14,R.drawable.ant3,R.drawable.ant7,R.drawable.ant11,R.drawable.ant15,R.drawable.ant4,R.drawable.ant8,R.drawable.ant12,R.drawable.blank};
    int[] image2={R.drawable.ds1,R.drawable.ds2,R.drawable.ds3,R.drawable.ds4,R.drawable.ds5,R.drawable.ds6,R.drawable.ds7,R.drawable.ds8,R.drawable.ds9,R.drawable.ds10,R.drawable.ds11,R.drawable.ds12,R.drawable.ds13,R.drawable.ds14,R.drawable.ds15,R.drawable.blank};
    int[] image3={R.drawable.fal1,R.drawable.fal2,R.drawable.fal3,R.drawable.fal4,R.drawable.fal5,R.drawable.fal6,R.drawable.fal7,R.drawable.fal8,R.drawable.fal9,R.drawable.fal10,R.drawable.fal11,R.drawable.fal12,R.drawable.fal13,R.drawable.fal14,R.drawable.fal15,R.drawable.blank};
    int[] image4={R.drawable.vis1,R.drawable.vis2,R.drawable.vis3,R.drawable.vis4,R.drawable.vis5,R.drawable.vis6,R.drawable.vis7,R.drawable.vis8,R.drawable.vis9,R.drawable.vis10,R.drawable.vis11,R.drawable.vis12,R.drawable.vis13,R.drawable.vis14,R.drawable.vis15,R.drawable.blank};
    int[] image5={R.drawable.wm1,R.drawable.wm2,R.drawable.wm3,R.drawable.wm4,R.drawable.wm5,R.drawable.wm6,R.drawable.wm7,R.drawable.wm8,R.drawable.wm9,R.drawable.wm10,R.drawable.wm11,R.drawable.wm12,R.drawable.wm13,R.drawable.wm14,R.drawable.wm15,R.drawable.blank};
    int[] image6={R.drawable.ws1,R.drawable.ws2,R.drawable.ws3,R.drawable.ws4,R.drawable.ws5,R.drawable.ws6,R.drawable.ws7,R.drawable.ws8,R.drawable.ws9,R.drawable.ws10,R.drawable.ws11,R.drawable.ws12,R.drawable.ws13,R.drawable.ws14,R.drawable.ws15,R.drawable.blank};
   // int[] image2={R.drawable.hawkeye1,R.drawable.hawkeye2,R.drawable.hawkeye3,R.drawable.hawkeye4,R.drawable.hawkeye5,R.drawable.hawkeye6,R.drawable.hawkeye7,R.drawable.hawkeye8,R.drawable.hawkeye9};
   // int[] image3={R.drawable.artboard_1,R.drawable.artboard_2,R.drawable.artboard_3,R.drawable.artboard_4,R.drawable.artboard_5,R.drawable.artboard_6,R.drawable.artboard_7,R.drawable.artboard_8,R.drawable.artboard_9};
   // int[] image4={R.drawable.supper_1,R.drawable.supper_2,R.drawable.supper_3,R.drawable.supper_4,R.drawable.supper_5,R.drawable.supper_6,R.drawable.supper_7,R.drawable.supper_8,R.drawable.supper_9};
    private static final int COLUMNS = 4;



    @Override
    protected void onStart() {
        super.onStart();

        unlock();

        downtoup();
        lefttoright();
        righttoleft();
        uptodown();


        setAnimatin();
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

        setAnimatin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        downtoup();
        lefttoright();
        righttoleft();
        uptodown();

        setAnimatin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        downtoup();
        lefttoright();
        righttoleft();
        uptodown();


        img1=(ImageView)findViewById(R.id.one);
        img2=(ImageView)findViewById(R.id.two);
        img3=(ImageView)findViewById(R.id.three);
        img4=(ImageView)findViewById(R.id.four);
        img5=(ImageView)findViewById(R.id.five);
        img6=(ImageView)findViewById(R.id.six);

        lock1=(ImageView)findViewById(R.id.lockm1);
        lock2=(ImageView)findViewById(R.id.lockm2);
        lock3=(ImageView)findViewById(R.id.lockm3);
        lock4=(ImageView)findViewById(R.id.lockm4);
        lock5=(ImageView)findViewById(R.id.lockm5);

        setAnimatin();


        final NextStageOper newstge = new NextStageOper();

        unlock();

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(newstge.mediamStage1) {

                        Intent in = new Intent(MediumClass.this, GameActivity.class);
                        in.putExtra("Game1", image1);
                        in.putExtra("COLUMS", COLUMNS);
                        in.putExtra("picture",antman);
                        in.putExtra("imageName","antMan");
                        in.putExtra("mode","MEDIUM");

                        //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);


                        startActivity(in);
                     //   finish();

                    }
                }
            });





        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences  sharedPreferences=getSharedPreferences("M",MODE_PRIVATE);

                if(sharedPreferences.getBoolean("m1",false)) {

                    Intent in = new Intent(MediumClass.this, GameActivity.class);
                    in.putExtra("Game1", image2);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",doctoreStrane);
                    in.putExtra("imageName","doctoreStrane");
                    in.putExtra("mode","MEDIUM");

                    //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);


                    startActivity(in);
                  //  finish();
                }else {

                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }


            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences  sharedPreferences=getSharedPreferences("M",MODE_PRIVATE);
                if(sharedPreferences.getBoolean("m2",false)) {
                    Intent in = new Intent(MediumClass.this, GameActivity.class);
                    in.putExtra("Game1", image3);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",falson);
                    in.putExtra("imageName","falon");
                    in.putExtra("mode","MEDIUM");

                   // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);


                    startActivity(in);
                   // finish();
                }else {

                   // Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();

                }

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences  sharedPreferences=getSharedPreferences("M",MODE_PRIVATE);
                if(sharedPreferences.getBoolean("m3",false)) {

                    Intent in = new Intent(MediumClass.this, GameActivity.class);
                    in.putExtra("Game1", image4);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",vison);
                    in.putExtra("imageName","vison");
                    in.putExtra("mode","MEDIUM");


                    // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);


                    startActivity(in);
                 //   finish();
                }else {
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();

                }


            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences  sharedPreferences=getSharedPreferences("M",MODE_PRIVATE);
            if(sharedPreferences.getBoolean("m4",false)) {
                Intent in = new Intent(MediumClass.this, GameActivity.class);
                in.putExtra("Game1", image5);
                in.putExtra("COLUMS", COLUMNS);
                in.putExtra("picture",warmansing);
                in.putExtra("imageName","warmansing");
                in.putExtra("mode","MEDIUM");

              //  in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(in);
               // finish();

            }else {
              //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();

            }

            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences  sharedPreferences=getSharedPreferences("M",MODE_PRIVATE);
                if(sharedPreferences.getBoolean("m5",false)) {
                    Intent in = new Intent(MediumClass.this, GameActivity.class);
                    in.putExtra("Game1", image6);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",winter_soldier);
                    in.putExtra("imageName","Winter Soldier");
                    in.putExtra("mode","MEDIUM");
                    //  in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(in);
                    // finish();

                }else {
                   // Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();

                }

            }
        });





    }


    private void setAnimatin() {

        CardView card1=findViewById(R.id.card1);
        CardView card2=findViewById(R.id.card2);
        CardView card3=findViewById(R.id.card3);
        CardView card4=findViewById(R.id.card4);
        CardView card5=findViewById(R.id.card5);
        CardView card6=findViewById(R.id.card6);

        TextView text=findViewById(R.id.txt);

        card1.setAnimation(lefttorightanimation);
        card2.setAnimation(righttoleft);
        card3.setAnimation(lefttorightanimation);
        card4.setAnimation(righttoleft);
        card5.setAnimation(lefttorightanimation);
        card6.setAnimation(righttoleft);


        text.setAnimation(uptodown);
    }

    private void righttoleft() {
        righttoleft=new TranslateAnimation(1000,0,0,0);
        righttoleft.setDuration(400);
        righttoleft.setFillAfter(true);
    }

    private void lefttoright() {
        lefttorightanimation=new TranslateAnimation(-1000,0,0,0);
        lefttorightanimation.setDuration(400);
        lefttorightanimation.setFillAfter(true);
    }

    private void downtoup() {
        downtoupanimation=new TranslateAnimation(0,0,1000,0);
        downtoupanimation.setDuration(400);
        downtoupanimation.setFillAfter(true);
    }


    private void uptodown() {
        uptodown=new TranslateAnimation(0,0,-500,0);
        uptodown.setDuration(500);
        uptodown.setFillAfter(true);
    }



    public void unlock(){
        SharedPreferences sharedPreferences=getSharedPreferences("M",MODE_PRIVATE);

        if (sharedPreferences.getBoolean("m1",false)){

            lock1.setVisibility(View.GONE);

        }else {

            lock1.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("m2",false)){
            lock2.setVisibility(View.GONE);
        }else {
            lock2.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("m3",false)){
            lock3.setVisibility(View.GONE);
        }else {
            lock3.setVisibility(View.VISIBLE);
        }


        if (sharedPreferences.getBoolean("m4",false)){
            lock4.setVisibility(View.GONE);
        }else {

            lock4.setVisibility(View.VISIBLE);
        }
        if (sharedPreferences.getBoolean("m5",false)){
            lock5.setVisibility(View.GONE);
        }else {

            lock5.setVisibility(View.VISIBLE);
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);


    }
}
