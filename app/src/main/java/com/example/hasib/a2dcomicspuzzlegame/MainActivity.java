package com.example.hasib.a2dcomicspuzzlegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.MyBounceInterpolator;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;

//import static com.example.hasib.a2dcomicspuzzlegame.Splashscreen.mediaPlayer;


public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    int a=0;
    private AdView mAdView;
    private SharedPreferences sp;

    TranslateAnimation downtoupanimation;
    TranslateAnimation lefttorightanimation;
    TranslateAnimation righttoleft;
    TranslateAnimation uptodown;

    InterstitialAd mInterstitialAd;
    RewardedVideoAd mAd;

    public void showInterstitial()
    {
        if(mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }else
        {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.start();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.start();
        }
        downtoup();
        lefttoright();
        righttoleft();
        uptodown();
        setAnimation();
    }



    Button play,option,about;
    ImageView avengerimage;
    final Context context = this;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    LinearLayout layout;

    private void dialog(String menu)
    {
        AlertDialog.Builder  dialog = new AlertDialog.Builder(this);
        dialog.setTitle("This is "+menu);
        dialog.setMessage("Are you want to cancle it");
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        dialog.show();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
      //    onRestart();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        play = (Button) findViewById(R.id.play);
        option = (Button) findViewById(R.id.option);
        about = (Button) findViewById(R.id.about);
        //getActionBar().setHomeButtonEnabled(true);
        setAnimation();

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        mAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());



        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/8691691433");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });



        sharedPreferences = getSharedPreferences("SP", MODE_PRIVATE);
        editor = sharedPreferences.edit();


         onUserLeaveHint();






        NextStageOper.mediamStage1 = true;
        NextStageOper.mediamStage2 = false;
        NextStageOper.mediamStage3 = false;
        NextStageOper.mediamStage4 = false;
        NextStageOper.mediamStage5 = false;

       play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);


               MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
               myAnim.setInterpolator(interpolator);

               play.startAnimation(myAnim);

               startActivity(new Intent(MainActivity.this,ModeActivity.class));
               overridePendingTransition(R.anim.enter, R.anim.exit);


           }
       });


        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);

                option.startAnimation(myAnim);


                startActivity(new Intent(MainActivity.this, Option.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
                myAnim.setInterpolator(interpolator);

                about.startAnimation(myAnim);


                startActivity(new Intent(MainActivity.this, about.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);

              /*  final AlertDialog.Builder alrt=new AlertDialog.Builder(context);
                // alrt.setMessage("Please put your name to remember you ");
                View v=LayoutInflater.from(context).inflate(R.layout.about,null);
                alrt.setView(v);
                alrt.setCancelable(true);



                alrt.show();*/
            }
        });

    }




    private void setAnimation() {

        avengerimage=findViewById(R.id.avenger_image);

        layout=findViewById(R.id.menu_section);

        layout.setAnimation(downtoupanimation);
       /* backbutton.setAnimation(lefttorightanimation);
        balbutton.setAnimation(righttoleft);*/
        avengerimage.setAnimation(uptodown);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       /* if(Variable.mediaPlayer.isPlaying() ) {
            Variable.mediaPlayer.pause();
        }else if (a==1){
            return;
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Variable.mediaPlayer.isPlaying()){
            Variable.mediaPlayer.pause();
        }
    }




    @Override
    protected void onStop() {
        super.onStop();
      /*  if (Variable.mediaPlayeron.isPlaying()){
            Variable.mediaPlayer.pause();
        }*/
    }

    /*@Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        if(Variable.mediaPlayer.isPlaying() ) {
            Variable.mediaPlayer.pause();
        }

    }*/

    @Override
    public void onBackPressed() {
         //super.onBackPressed();


        showInterstitial();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

       // android.os.Process.killProcess(android.os.Process.myPid());
    }


    public  void uptodown() {
        uptodown=new TranslateAnimation(0,0,-1000,0);
        uptodown.setDuration(400);
        uptodown.setFillAfter(true);
    }

    public  void righttoleft() {
        righttoleft=new TranslateAnimation(1000,0,0,0);
        righttoleft.setDuration(400);
        righttoleft.setFillAfter(true);
    }

    public  void lefttoright() {
        lefttorightanimation=new TranslateAnimation(-1000,0,0,0);
        lefttorightanimation.setDuration(400);
        lefttorightanimation.setFillAfter(true);
    }

    public  void downtoup() {
        downtoupanimation=new TranslateAnimation(0,0,1000,0);
        downtoupanimation.setDuration(400);
        downtoupanimation.setFillAfter(true);
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
