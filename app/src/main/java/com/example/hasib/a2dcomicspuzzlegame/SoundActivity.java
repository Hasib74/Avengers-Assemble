package com.example.hasib.a2dcomicspuzzlegame;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.MyBounceInterpolator;
import com.example.hasib.a2dcomicspuzzlegame.PuzzeAnimation.AnimationPuzze;
import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;

import java.io.IOException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import static com.example.hasib.a2dcomicspuzzlegame.Splashscreen.mediaPlayer;

public class SoundActivity extends AppCompatActivity {

    ImageView sound_setup;
    SharedPreferences sp;
   // SharedPreferences sp;
    //SharedPreferences.Editor ed;
   // MediaPlayer m  ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );





        AnimationPuzze.downtoup();
        AnimationPuzze.lefttoright();
        AnimationPuzze.righttoleft();
        AnimationPuzze.uptodown();

        CardView card=findViewById(R.id.cardView);
        sound_setup = (ImageView) findViewById(R.id.music_setup_imageview);

        card.setAnimation(AnimationPuzze.downtoupanimation);

        sound_setup.setAnimation(AnimationPuzze.lefttorightanimation);
       // mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
    //  m = MediaPlayer.create(getApplicationContext(), R.raw.music);



    /*    final Animation myAnim = AnimationUtils.loadAnimation(SoundActivity.this, R.anim.activity_animation);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(4, 4);

        myAnim.setInterpolator(interpolator);
        //overridePendingTransition(R.anim.enter, R.anim.exit);

        card.startAnimation(myAnim);*/



        sound_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               // sound();
               sp = getSharedPreferences("Music",MODE_PRIVATE);

                if( sp.getBoolean("music",true)) {
                    musicOff();
                    sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.nosound));
                }
                else if( !sp.getBoolean("music",true))
                {
                    musicOn();
                    sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.mutebutton));
                }

            }
        });
    }


    private void musicOff()
    {
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("music", false);
        Common.isPlaying=false;
        e.commit();
        mediaPlayer.pause();
    }

    private void musicOn()
    {
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("music",true);
        ed.commit();
        Common.isPlaying=true;
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    private void sound()
    {
      sp = getSharedPreferences("Music",MODE_PRIVATE);
        if( sp.getBoolean("music",true) )
        {
            sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.mutebutton));
        }
        else if(!sp.getBoolean("music",true))
        {
            sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.nosound));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sound();
    }


}
