package com.example.hasib.a2dcomicspuzzlegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
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

public class HardClass extends AppCompatActivity {
    private static final int COLUMNS = 5;
    ImageView img1,img2,img3,img4,img5,img6;
    ImageView lock1,lock2,lock3,lock4,lock5;


    TranslateAnimation downtoupanimation;
    TranslateAnimation lefttorightanimation;
    TranslateAnimation righttoleft;
    TranslateAnimation uptodown;

    int supperMan=R.drawable.supperman;
    int blackPanther=R.drawable.blackpanther;
    int quickSilver=R.drawable.quicksilver;
    int scarl=R.drawable.scarlterwitch;
    int wasp=R.drawable.wasp;
    int cm=R.drawable.captin_marvel;

    int[] image1={R.drawable.sp1,R.drawable.sp2,R.drawable.sp3,R.drawable.sp4,R.drawable.sp5,R.drawable.sp6,R.drawable.sp7,R.drawable.sp8,R.drawable.sp9,R.drawable.sp10,R.drawable.sp11,R.drawable.sp12,R.drawable.sp13,R.drawable.sp14,R.drawable.sp15,R.drawable.sp16,R.drawable.sp17,R.drawable.sp18,R.drawable.sp19,R.drawable.sp20,R.drawable.sp21,R.drawable.sp22,R.drawable.sp23,R.drawable.sp24,R.drawable.blank};
    int[] image2={R.drawable.bp1,R.drawable.bp2,R.drawable.bp3,R.drawable.bp4,R.drawable.bp5,R.drawable.bp6,R.drawable.bp7,R.drawable.bp8,R.drawable.bp9,R.drawable.bp10,R.drawable.bp11,R.drawable.bp12,R.drawable.bp13,R.drawable.bp14,R.drawable.bp15,R.drawable.bp16,R.drawable.bp17,R.drawable.bp18,R.drawable.bp19,R.drawable.bp20,R.drawable.bp21,R.drawable.bp22,R.drawable.bp23,R.drawable.bp24,R.drawable.blank};
    int[] image3={R.drawable.qs1,R.drawable.qs2,R.drawable.qs3,R.drawable.qs4,R.drawable.qs5,R.drawable.qs6,R.drawable.qs7,R.drawable.qs8,R.drawable.qs9,R.drawable.qs10,R.drawable.qs11,R.drawable.qs12,R.drawable.qs13,R.drawable.qs14,R.drawable.qs15,R.drawable.qs16,R.drawable.qs17,R.drawable.qs18,R.drawable.qs19,R.drawable.qs20,R.drawable.qs21,R.drawable.qs22,R.drawable.qs23,R.drawable.qs24,R.drawable.blank};
    int[] image4={R.drawable.wanda1,R.drawable.wanda2,R.drawable.wanda3,R.drawable.wanda4,R.drawable.wanda5,R.drawable.wanda6,R.drawable.wanda7,R.drawable.wanda8,R.drawable.wanda9,R.drawable.wanda10,R.drawable.wanda11,R.drawable.wanda12,R.drawable.wanda13,R.drawable.wanda14,R.drawable.wanda15,R.drawable.wanda16,R.drawable.wanda17,R.drawable.wanda18,R.drawable.wanda19,R.drawable.wanda20,R.drawable.wanda21,R.drawable.wanda22,R.drawable.wanda23,R.drawable.wanda24,R.drawable.blank};
    int[] image5={R.drawable.wasp1,R.drawable.wasp2,R.drawable.wasp3,R.drawable.wasp4,R.drawable.wasp5,R.drawable.wasp6,R.drawable.wasp7,R.drawable.wasp8,R.drawable.wasp9,R.drawable.wasp10,R.drawable.wasp11,R.drawable.wasp12,R.drawable.wasp13,R.drawable.wasp14,R.drawable.wasp15,R.drawable.wasp16,R.drawable.wasp17,R.drawable.wasp18,R.drawable.wasp19,R.drawable.wasp20,R.drawable.wasp21,R.drawable.wasp22,R.drawable.wasp23,R.drawable.wasp24,R.drawable.blank};
    int[] image6={R.drawable.cm1,R.drawable.cm2,R.drawable.cm3,R.drawable.cm4,R.drawable.cm5,R.drawable.cm6,R.drawable.cm7,R.drawable.cm8,R.drawable.cm9,R.drawable.cm10,R.drawable.cm11,R.drawable.cm12,R.drawable.cm13,R.drawable.cm14,R.drawable.cm15,R.drawable.cm16,R.drawable.cm17,R.drawable.cm18,R.drawable.cm19,R.drawable.cm20,R.drawable.cm21,R.drawable.cm22,R.drawable.cm23,R.drawable.cm24,R.drawable.blank};
   /* SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;*/

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
    protected void onResume()
    {
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_hard_class);
        img1=(ImageView)findViewById(R.id.one);
        img2=(ImageView)findViewById(R.id.two);
        img3=(ImageView)findViewById(R.id.three);
        img4=(ImageView)findViewById(R.id.four);
        img5=(ImageView)findViewById(R.id.five);
        img6=(ImageView)findViewById(R.id.six);

        lock1=(ImageView)findViewById(R.id.lockh1);
        lock2=(ImageView)findViewById(R.id.lockh2);
        lock3=(ImageView)findViewById(R.id.lockh3);
        lock4=(ImageView)findViewById(R.id.lockh4);
        lock5=(ImageView)findViewById(R.id.lockh5);


        downtoup();
        lefttoright();
        righttoleft();
        uptodown();

        setAnimatin();





        final NextStageOper newstge = new NextStageOper();




        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(newstge.hard1) {

                    Intent in = new Intent(HardClass.this, GameActivity.class);
                    in.putExtra("Game1", image1);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",supperMan);
                    in.putExtra("imageName","supperMan");
                    in.putExtra("mode","HARD");



                    startActivity(in);
                //    finish();


                }

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=HardClass.this.getSharedPreferences("H",MODE_PRIVATE);

                if(sp.getBoolean("h1",false)) {

                    Intent in = new Intent(HardClass.this, GameActivity.class);
                    //  in.putExtra("Game1",image2);
                    in.putExtra("Game1", image2);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",blackPanther);
                    in.putExtra("imageName","blackPanther");
                    in.putExtra("mode","HARD");




                    startActivity(in);
                //    finish();


                }else {
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }


            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=HardClass.this.getSharedPreferences("H",MODE_PRIVATE);
                if(sp.getBoolean("h2",false)) {
                    Intent in = new Intent(HardClass.this, GameActivity.class);
                    //  in.putExtra("Game1",image3);
                    in.putExtra("Game1", image3);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",quickSilver);
                    in.putExtra("imageName","quickSilver");
                    in.putExtra("mode","HARD");



                    startActivity(in);
                //    finish();

                }else {
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=HardClass.this.getSharedPreferences("H",MODE_PRIVATE);
                if(sp.getBoolean("h3",false)) {

                    Intent in = new Intent(HardClass.this, GameActivity.class);
                    // in.putExtra("Game1",image4);
                    in.putExtra("Game1", image4);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",scarl);
                    in.putExtra("imageName","scarl");
                    in.putExtra("mode","HARD");




                    startActivity(in);
            //        finish();

                }else {
                //    Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }


            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=HardClass.this.getSharedPreferences("H",MODE_PRIVATE);
                if(sp.getBoolean("h4",false)) {

                    Intent in = new Intent(HardClass.this, GameActivity.class);
                    // in.putExtra("Game1",image4);
                    in.putExtra("Game1", image5);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",wasp);
                    in.putExtra("imageName","wasp");
                    in.putExtra("mode","HARD");




                    startActivity(in);
             //       finish();


                }else {
                  //  Toast.makeText(getApplicationContext(),"Please cover the previous game first",Toast.LENGTH_SHORT).show();
                }


            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=HardClass.this.getSharedPreferences("H",MODE_PRIVATE);
                if(sp.getBoolean("h5",false)) {

                    Intent in = new Intent(HardClass.this, GameActivity.class);
                    // in.putExtra("Game1",image4);
                    in.putExtra("Game1", image6);
                    in.putExtra("COLUMS", COLUMNS);
                    in.putExtra("picture",cm);
                    in.putExtra("imageName","Captain Marvel");
                    in.putExtra("mode","HARD");

                    startActivity(in);//       finish();

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
        uptodown=new TranslateAnimation(0,0,-400,0);
        uptodown.setDuration(400);
        uptodown.setFillAfter(true);
    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(HardClass.this,MainActivity.class));
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);

    }

    public void unlock(){
        SharedPreferences sharedPreferences=getSharedPreferences("H",MODE_PRIVATE);

        if (sharedPreferences.getBoolean("h1",false)){

            lock1.setVisibility(View.GONE);

        }else {

            lock1.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("h2",false)){
            lock2.setVisibility(View.GONE);
        }else {
            lock2.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("h3",false)){
            lock3.setVisibility(View.GONE);
        }else {
            lock3.setVisibility(View.VISIBLE);
        }


        if (sharedPreferences.getBoolean("h4",false)){
            lock4.setVisibility(View.GONE);
        }else {

            lock4.setVisibility(View.VISIBLE);
        }

        if (sharedPreferences.getBoolean("h5",false)){
            lock5.setVisibility(View.GONE);
        }else {

            lock5.setVisibility(View.VISIBLE);
        }

    }
}
