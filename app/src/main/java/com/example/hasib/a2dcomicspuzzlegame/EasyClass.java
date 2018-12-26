package com.example.hasib.a2dcomicspuzzlegame;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasib.a2dcomicspuzzlegame.Model.RevealAnimation;

import org.w3c.dom.Text;
import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;

public class EasyClass extends AppCompatActivity {


    ImageView img1,img2,img3,img4,img5,img6;
    int halk=R.drawable.halk;
    int hawkeye=R.drawable.hawkeye;
    int captainAmirica=R.drawable.captainamrica;
    int ironman=R.drawable.ironman;
    int thor=R.drawable.thor;
    int blackwidow=R.drawable.black_widow;
    Button backbutton;
    TextView title;
    ImageView lock1,lock2,lock3,lock4,lock5;
    RevealAnimation mRevealAnimation;

    int[] image1={R.drawable.halk1,R.drawable.halk4,R.drawable.hak7,R.drawable.halk2,R.drawable.halk5,R.drawable.halk8,R.drawable.halk3,R.drawable.halk6,R.drawable.blank};
    int[] image2={R.drawable.hawkeye1,R.drawable.hawkeye4,R.drawable.hawkeye7,R.drawable.hawkeye2,R.drawable.hawkeye5,R.drawable.hawkeye8,R.drawable.hawkeye3,R.drawable.hawkeye6,R.drawable.blank};
    int[] image3={R.drawable.artboard_1,R.drawable.artboard_4,R.drawable.artboard_7,R.drawable.artboard_2,R.drawable.artboard_5,R.drawable.artboard_8,R.drawable.artboard_3,R.drawable.artboard_6,R.drawable.blank};
    int[] image4={R.drawable.supper_1,R.drawable.supper_4,R.drawable.supper_7,R.drawable.supper_2,R.drawable.supper_5,R.drawable.supper_8,R.drawable.supper_3,R.drawable.supper_6,R.drawable.blank};
    int [] image5={R.drawable.th1,R.drawable.th2,R.drawable.th3,R.drawable.th4,R.drawable.th5,R.drawable.th6,R.drawable.th7,R.drawable.th8,R.drawable.blank};
    int [] image6={R.drawable.bw1,R.drawable.bw2,R.drawable.bw3,R.drawable.bw4,R.drawable.bw5,R.drawable.bw6,R.drawable.bw7,R.drawable.bw8,R.drawable.blank};

    private  int COLUMNS = 3;
    final NextStageOper newstge = new NextStageOper();
    TranslateAnimation downtoupanimation;
    TranslateAnimation lefttorightanimation;
    TranslateAnimation righttoleft;
    TranslateAnimation uptodown;
    int one=1;
    @Override
    protected void onStart() {
        super.onStart();
        if (!Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.start();
        }
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
       /* sp = getSharedPreferences("Music", MODE_PRIVATE);
        if (sp.getBoolean("music", true)) {
            mediaPlayer.pause();
        }*/
        if (Common.isPlaying){

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
    protected void onPause() {
        super.onPause();
        if (Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        downtoup();
        lefttoright();
        righttoleft();
        uptodown();
        setAnimatin();


        if (!Variable.mediaPlayer.isPlaying() ){
            Variable.mediaPlayer.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_class);
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
        title=(TextView)findViewById(R.id.txt);

        lock1=(ImageView) findViewById(R.id.lockhaw1);
        lock2=(ImageView) findViewById(R.id.lockcap2);
        lock3=(ImageView) findViewById(R.id.lockIron3);
        lock4=(ImageView) findViewById(R.id.lockthor4);
        lock5=(ImageView) findViewById(R.id.lockblack5);
       // lock5=(ImageView) findViewById(R.id.lockblack5);




       setAnimatin();



        Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/txtfont.ttf");
        title.setTypeface(mFont);





        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(newstge.easyStage1) {
/*
                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image1);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",halk);
                    in.putExtra("imageName","halk");*/


                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image2);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",hawkeye);
                    in.putExtra("imageName","hawkey");
                    in.putExtra("mode","EASY");
                    //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);



                    //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    //in.putExtra()
                    //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    //finish();

                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                SharedPreferences sharedPreferences=EasyClass.this.getSharedPreferences("test",MODE_PRIVATE);


                if(sharedPreferences.getBoolean("solve1",false)) {





                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image1);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",halk);
                    in.putExtra("imageName","halk");
                    in.putExtra("mode","EASY");
                    startActivity(in);
                       // finish();
                    }else {
                      //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                    }


            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=EasyClass.this.getSharedPreferences("test",MODE_PRIVATE);

                if (sharedPreferences.getBoolean("solve2",false)){
                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image3);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",captainAmirica);
                    in.putExtra("imageName","captainAmrica");
                    in.putExtra("mode","EASY");

                   // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                 //   in.putExtra("easy","three");


                startActivity(in);
              //  finish();



            }else{
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }

            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=EasyClass.this.getSharedPreferences("test",MODE_PRIVATE);
                if(sharedPreferences.getBoolean("solve3",false)) {


                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image4);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",ironman);
                    in.putExtra("imageName","IronMan");
                    in.putExtra("mode","EASY");

                    ///in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);



                    startActivity(in);

//                    finish();


                }else {
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }


            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=EasyClass.this.getSharedPreferences("test",MODE_PRIVATE);
                if(  sharedPreferences.getBoolean("solve4",false)) {

                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image5);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",thor);
                    in.putExtra("imageName","thor");
                    in.putExtra("mode","EASY");

                   /// in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);






                    startActivity(in);
  //                  finish();


                }else {
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }


            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=EasyClass.this.getSharedPreferences("test",MODE_PRIVATE);


                if(sharedPreferences.getBoolean("solve5",false)) {

                    Intent in = new Intent(EasyClass.this, GameActivity.class);
                    in.putExtra("Game1", image6);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",blackwidow);
                    in.putExtra("imageName","blackWidow");

                    in.putExtra("mode","EASY");



                   // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);




                    startActivity(in);


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


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);


    }

    public void unlock(){

      //  Toast.makeText(this,"easy unlock section",Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);

        if (sharedPreferences.getBoolean("solve1",false)){

            lock1.setVisibility(View.GONE);

        }else {

            lock1.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("solve2",false)){
            lock2.setVisibility(View.GONE);
        }else {
            lock2.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("solve3",false)){
            lock3.setVisibility(View.GONE);
        }else {
            lock3.setVisibility(View.VISIBLE);
        }


        if (sharedPreferences.getBoolean("solve4",false)){
            lock4.setVisibility(View.GONE);
        }else {

            lock4.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("solve5",false)){
            lock5.setVisibility(View.GONE);
        }else {
            lock5.setVisibility(View.VISIBLE);
        }


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
        uptodown=new TranslateAnimation(0,0,-400,0);
        uptodown.setDuration(400);
        uptodown.setFillAfter(true);
    }




}
