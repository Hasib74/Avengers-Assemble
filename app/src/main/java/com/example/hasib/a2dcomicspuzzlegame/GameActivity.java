package com.example.hasib.a2dcomicspuzzlegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasib.a2dcomicspuzzlegame.DatabaseBoos.GameDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class GameActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayerSawp;
    private AdView mAdView;


    public boolean solveGame = false;
    public static int[] getPicture;
    public static Boolean isPlay;
    private Button Save;
    public static String scoreTime;
    public static Double temLowpScore = 0.0;
    public static Double temHighpScore = 999.0;
    public static TextView timeView;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    ImageButton back;


    //private  int unicpic;
    private static GestureDetectGridView mGridView;      //object of GestureDetectGridView class

    private static int COLUMNS = 4;                  // defind the colums
    private static int DIMENSIONS = COLUMNS * COLUMNS; //defind the daimention

    private static int mColumnWidth, mColumnHeight;          //defind the colum width and Hight

    public static final String up = "up";                   //up
    public static final String down = "down";               //down
    public static final String left = "left";               //left
    public static final String right = "right";// right

    private static String[] tileList;//String List
    static AlertDialog dialog;

    public int number;
    boolean isSolveGame;
    ImageView setPicture;
    public static int unicpic;
    RelativeLayout relativeLayout;
    String bl;
    //public MainActivity activity;
    private int seconds = 0;
    private boolean startRun = true;
    TextView textView;
    TextView title;
    static long MillisecondTime;
    static long StartTime;
    static long TimeBuff;
    static long UpdateTime = 0L;
    public static android.os.Handler handler = new android.os.Handler();
    static int Seconds;
    static int Minutes;
    static int MilliSeconds;
    Context context;
    public static String imgreferance;
    String txt;
    private String Mode;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        sharedPreferences = this.getSharedPreferences("SP", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        unicpic = getIntent().getIntExtra("picture", 0);
        getPicture = getIntent().getIntArrayExtra("Game1");
        isSolveGame = getIntent().getBooleanExtra("UNIQID", false);
        number = getPicture.length;
        imgreferance = getIntent().getStringExtra("imageName");
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        timeView = (TextView) findViewById(R.id.timeview);
        setPicture = (ImageView) findViewById(R.id.picture);
        title = findViewById(R.id.highScoreText);
        setPicture.setImageResource(unicpic);
        title.setText(getIntent().getStringExtra("mode"));
        COLUMNS = getIntent().getIntExtra("COLUMS", 1);
        DIMENSIONS = COLUMNS * COLUMNS;
        // Toast.makeText(getApplicationContext(),"Picture Name :-"+imgreferance,Toast.LENGTH_SHORT).show();

        setPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createCustomDialog(unicpic);

            }
        });


        isPlay = true;
        start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Variable.mediaPlayer.isPlaying()) {
            Variable.mediaPlayer.pause();
        }

        init();
        scramble();
        setDimensions();
    }

    public GameActivity() {


    }

    private void init() {
        //  mGridView = (GestureDetectGridView) findViewById(R.id.grid); //initialisd the GestureDetectGridView which is in in activity
        mGridView.setNumColumns(COLUMNS);                            //set The Number of Colums

        tileList = new String[DIMENSIONS];                           // put daimention in the String array
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);                           // put all daimention in the titileList
        }
        start();
    }

    private void scramble() {
        int index;                                                 // index variable
        String temp;                                               // temp variable
        Random random = new Random();                              //make a oject of radom method which is of java libary method

        for (int i = tileList.length - 1; i > 0; i--) {           //(i=8;i>0;i--)
            index = random.nextInt(i + 1);
            temp = tileList[index];

            tileList[index] = tileList[i];
            tileList[i] = temp;


        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        if (COLUMNS == 3) {

            for (int i = 0; i < tileList.length; i++) {
                // run=true;
                button = new Button(context);
                if (tileList[i].equals("0"))
                    button.setBackgroundResource(getPicture[0]);
                else if (tileList[i].equals("1"))
                    button.setBackgroundResource(getPicture[1]);
                else if (tileList[i].equals("2"))
                    button.setBackgroundResource(getPicture[2]);
                else if (tileList[i].equals("3"))
                    button.setBackgroundResource(getPicture[3]);
                else if (tileList[i].equals("4"))
                    button.setBackgroundResource(getPicture[4]);
                else if (tileList[i].equals("5"))
                    button.setBackgroundResource(getPicture[5]);
                else if (tileList[i].equals("6"))
                    button.setBackgroundResource(getPicture[6]);
                else if (tileList[i].equals("7"))
                    button.setBackgroundResource(getPicture[7]);
                else if (tileList[i].equals("8"))
                    button.setBackgroundResource(getPicture[8]);

                buttons.add(button);
            }
        } else if (COLUMNS == 4) {

            for (int i = 0; i < tileList.length; i++) {
                button = new Button(context);

                if (tileList[i].equals("0"))
                    button.setBackgroundResource(getPicture[0]);
                else if (tileList[i].equals("1"))
                    button.setBackgroundResource(getPicture[1]);
                else if (tileList[i].equals("2"))
                    button.setBackgroundResource(getPicture[2]);
                else if (tileList[i].equals("3"))
                    button.setBackgroundResource(getPicture[3]);
                else if (tileList[i].equals("4"))
                    button.setBackgroundResource(getPicture[4]);
                else if (tileList[i].equals("5"))
                    button.setBackgroundResource(getPicture[5]);
                else if (tileList[i].equals("6"))
                    button.setBackgroundResource(getPicture[6]);
                else if (tileList[i].equals("7"))
                    button.setBackgroundResource(getPicture[7]);
                else if (tileList[i].equals("8"))
                    button.setBackgroundResource(getPicture[8]);
                else if (tileList[i].equals("9"))
                    button.setBackgroundResource(getPicture[9]);
                else if (tileList[i].equals("10"))
                    button.setBackgroundResource(getPicture[10]);
                else if (tileList[i].equals("11"))
                    button.setBackgroundResource(getPicture[11]);
                else if (tileList[i].equals("12"))
                    button.setBackgroundResource(getPicture[12]);
                else if (tileList[i].equals("13"))
                    button.setBackgroundResource(getPicture[13]);
                else if (tileList[i].equals("14"))
                    button.setBackgroundResource(getPicture[14]);
                else if (tileList[i].equals("15"))
                    button.setBackgroundResource(getPicture[15]);

                buttons.add(button);

            }
        } else if (COLUMNS == 5) {
            for (int i = 0; i < tileList.length; i++) {
                button = new Button(context);

                if (tileList[i].equals("0"))
                    button.setBackgroundResource(getPicture[0]);
                else if (tileList[i].equals("1"))
                    button.setBackgroundResource(getPicture[1]);
                else if (tileList[i].equals("2"))
                    button.setBackgroundResource(getPicture[2]);
                else if (tileList[i].equals("3"))
                    button.setBackgroundResource(getPicture[3]);
                else if (tileList[i].equals("4"))
                    button.setBackgroundResource(getPicture[4]);
                else if (tileList[i].equals("5"))
                    button.setBackgroundResource(getPicture[5]);
                else if (tileList[i].equals("6"))
                    button.setBackgroundResource(getPicture[6]);
                else if (tileList[i].equals("7"))
                    button.setBackgroundResource(getPicture[7]);
                else if (tileList[i].equals("8"))
                    button.setBackgroundResource(getPicture[8]);
                else if (tileList[i].equals("9"))
                    button.setBackgroundResource(getPicture[9]);
                else if (tileList[i].equals("10"))
                    button.setBackgroundResource(getPicture[10]);
                else if (tileList[i].equals("11"))
                    button.setBackgroundResource(getPicture[11]);
                else if (tileList[i].equals("12"))
                    button.setBackgroundResource(getPicture[12]);
                else if (tileList[i].equals("13"))
                    button.setBackgroundResource(getPicture[13]);
                else if (tileList[i].equals("14"))
                    button.setBackgroundResource(getPicture[14]);
                else if (tileList[i].equals("15"))
                    button.setBackgroundResource(getPicture[15]);
                else if (tileList[i].equals("16"))
                    button.setBackgroundResource(getPicture[16]);
                else if (tileList[i].equals("17"))
                    button.setBackgroundResource(getPicture[17]);
                else if (tileList[i].equals("18"))
                    button.setBackgroundResource(getPicture[18]);
                else if (tileList[i].equals("19"))
                    button.setBackgroundResource(getPicture[19]);
                else if (tileList[i].equals("20"))
                    button.setBackgroundResource(getPicture[20]);
                else if (tileList[i].equals("21"))
                    button.setBackgroundResource(getPicture[21]);
                else if (tileList[i].equals("22"))
                    button.setBackgroundResource(getPicture[22]);
                else if (tileList[i].equals("23"))
                    button.setBackgroundResource(getPicture[23]);
                else if (tileList[i].equals("24"))
                    button.setBackgroundResource(getPicture[24]);


                buttons.add(button);


            }
        }

      /*  for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(getPicture[0]);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(getPicture[1]);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(getPicture[2]);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(getPicture[3]);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(getPicture[4]);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(getPicture[5]);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(getPicture[6]);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(getPicture[7]);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(getPicture[8]);
            else if (tileList[i].equals("9"))
                button.setBackgroundResource(getPicture[9]);
            else if (tileList[i].equals("10"))
                button.setBackgroundResource(getPicture[10]);
            else if (tileList[i].equals("11"))
                button.setBackgroundResource(getPicture[11]);
            else if (tileList[i].equals("12"))
                button.setBackgroundResource(getPicture[12]);
            else if (tileList[i].equals("13"))
                button.setBackgroundResource(getPicture[13]);
            else if (tileList[i].equals("14"))
                button.setBackgroundResource(getPicture[14]);
            else if (tileList[i].equals("15"))
                button.setBackgroundResource(getPicture[15]);


            buttons.add(button);
        }*/


        isPlay = true;


        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));

    }


    private void swap(final Context context, final int currentPosition, final int swap) {
        android.os.Handler handler = new android.os.Handler(Looper.myLooper());
        handler.post(new Runnable() {


//            @SuppressLint("ResourceType")
//            ImageView imageView = findViewById(R.drawable.blank);


            // find blank drawer resource


            @Override
            public void run() {
                try {
                    // mediaPlayerSawp.prepare();


                    Toast.makeText(context, "Swap tile resource id: " + tileList[swap] + "Swap -->" + swap + "\n", Toast.LENGTH_SHORT).show();


                    mediaPlayerSawp = MediaPlayer.create(context, R.raw.swap);
                    mediaPlayerSawp.start();
                    String newPosition = tileList[currentPosition + swap];
                    tileList[currentPosition + swap] = tileList[currentPosition];
                    tileList[currentPosition] = newPosition;
                    display(context);

                } catch (Exception e) {

                } finally {

                }


                if (isSolved()) {

                    solveMethod(context);
                    push();


                }
            }

        });

        mGridView.enable(true);

    }

    private void solveMethod(Context context) {

        String time = timeView.getText().toString();

        if (COLUMNS == 3) {
            Mode = "Easy";

        } else if (COLUMNS == 4) {
            Mode = "Medium";
        } else {
            Mode = "Hard";
        }
        // Toast.makeText(context, "YOU WIN! " + Mode, Toast.LENGTH_SHORT).show();
        solveDialog(context, Mode, imgreferance, time);
        SharedPreferences sharedPreferences = context.getSharedPreferences("SP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (COLUMNS == 4) {
            SharedPreferences sp = context.getSharedPreferences("M", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();


            if (imgreferance.equals("antMan")) {
                ed.putBoolean("m1", true);
                ed.commit();

            } else if (imgreferance.equals("doctoreStrane")) {

                ed.putBoolean("m2", true);
                ed.commit();


            } else if (imgreferance.equals("falon")) {
                ed.putBoolean("m3", true);
                ed.commit();
            } else if (imgreferance.equals("vison")) {
                ed.putBoolean("m4", true);
                ed.commit();
            } else if (imgreferance.equals("warmansing")) {

                ed.putBoolean("m5", true);
                ed.commit();
                // solveDialog(context,unicpic);
            }
        } else if (COLUMNS == 3) {
            SharedPreferences sp = context.getSharedPreferences("test", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            if (imgreferance.equals("hawkey")) {
                ed.putBoolean("solve1", true);
                ed.commit();
            } else if (imgreferance.equals("halk")) {
                ed.putBoolean("solve2", true);
                ed.commit();
            } else if (imgreferance.equals("captainAmrica")) {
                ed.putBoolean("solve3", true);
                ed.commit();

            } else if (imgreferance.equals("IronMan")) {
                ed.putBoolean("solve4", true);
                ed.commit();

            } else if (imgreferance.equals("thor")) {
                ed.putBoolean("solve5", true);
                ed.commit();
            } else if (imgreferance.equals("blackWidow")) {
                ed.putBoolean("solve6", true);
                ed.commit();
            }


        } else if (COLUMNS == 5) {
            SharedPreferences sp = context.getSharedPreferences("H", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            if (imgreferance.equals("supperMan")) {
                ed.putBoolean("h1", true);
                ed.commit();
            } else if (imgreferance.equals("blackPanther")) {
                ed.putBoolean("h2", true);
                ed.commit();
            } else if (imgreferance.equals("quickSilver")) {
                ed.putBoolean("h3", true);
                ed.commit();
            } else if (imgreferance.equals("scarl")) {
                ed.putBoolean("h4", true);
                ed.commit();

            } else if (imgreferance.equals("wasp")) {
                ed.putBoolean("h5", true);
                ed.commit();

            }
        }
    }

    private void solveDialog(final Context context, final String mode, final String imgreferance, final String time) {
        MediaPlayer win_media;
        win_media = MediaPlayer.create(context, R.raw.winning_souund);
        win_media.start();

        AlertDialog.Builder alrt = new AlertDialog.Builder(context, R.style.MyDialogThem);

        final LayoutInflater inflater = LayoutInflater.from(context);
        View customDialog = inflater.inflate(R.layout.player_name, null);
        alrt.setTitle(" Win Message ");
        alrt.setIcon(R.drawable.logo_avangers);
        alrt.setMessage("Enter Your Name : ");
        alrt.setView(customDialog);

        final EditText getPlayerName = customDialog.findViewById(R.id.player_name);
        final TextView scoreTime = customDialog.findViewById(R.id.solving_time_textview);

        scoreTime.setText(time + "s");
        alrt.setCancelable(false);


        alrt.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                final AlertDialog.Builder alrt2 = new AlertDialog.Builder(context, R.style.MyDialogThem);

                // View customDialog = inflater.inflate(R.layout.player_name, null);
                alrt2.setTitle(" Next Step ");
                alrt2.setIcon(R.drawable.logo_avangers);
                alrt2.setPositiveButton(" Next ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ((Activity) context).finish();

                        SharedPreferences sp = context.getSharedPreferences("Music", MODE_PRIVATE);
                        if (sp.getBoolean("music", true)) {
                            Variable.mediaPlayer.start();
                            Variable.mediaPlayer.setLooping(true);
                        }
                        reset();

                    }
                });


                alrt2.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        scramble();
                        display(context);
                        reset();

                        start();

                    }
                });

                alrt2.setCancelable(false);
                alrt2.show();
                String name = getPlayerName.getText().toString();
                GameDatabase gameDatabase = new GameDatabase(context);
                //   Toast.makeText(context,"Name"+name  ,Toast.LENGTH_LONG).show();
                String check = gameDatabase.insertIntoTheDatabse(mode, name, time, imgreferance);
                //  Toast.makeText(context,""+check,Toast.LENGTH_LONG).show();


            }
        });
        alrt.show();
    }

    private void BackgroundMusic() {
        SharedPreferences sp = getSharedPreferences("Music", MODE_PRIVATE);
        if (sp.getBoolean("music", true)) {
            Variable.mediaPlayer.start();
            Variable.mediaPlayer.setLooping(true);
        }

    }


    public void moveTiles(Context context, String direction, int position) {

        Log.d("TAG", "moveTiles: " + tileList[position]);

        if (COLUMNS == 3) {


            if (position == 0) {


                //check


                if (direction.equals(right) /*&& Objects.equals(tileList[1], "8")*/)
                    swap(context, position, 1);
                else if (direction.equals(down) /*&& Objects.equals(tileList[COLUMNS], "8")*/)
                    swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-center tiles
            } else if (position > 0 && position < COLUMNS - 1) {


                Log.d("TAG", "moveTiles: " + tileList[0]);

                if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else if (direction.equals(right)) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-right-corner tile
            } else if (position == COLUMNS - 1) {


                if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Left-side tiles
            } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                    position % COLUMNS == 0) {
                if (direction.equals(up)) swap(context, position, -COLUMNS);
                else if (direction.equals(right)) swap(context, position, 1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Right-side AND bottom-right-corner tiles
            } else if (position == COLUMNS * 2 - 1) {
                if (direction.equals(up)) swap(context, position, -COLUMNS);
                else if (direction.equals(left)) swap(context, position, -1);
                    // else if (direction.equals(left) &&  tileList[7].equals("2")  ) swap(context, position, -1);
                else if (direction.equals(down)) {

                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


                // Bottom-left corner tile
            } else if (position == COLUMNS * 3 - 1) {
                if (direction.equals(up))
                    swap(context, position, -COLUMNS);
                    //else if (direction.equals(left) && tileList[4].equals("2")) swap(context, position, -1);
                else if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(down) && tileList[8].equals("2")) {
                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


            } else if (position == DIMENSIONS - COLUMNS) {
                if (direction.equals(up)) swap(context, position, -COLUMNS);
                else if (direction.equals(right)) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Bottom-center tiles
            } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
                if (direction.equals(up)) swap(context, position, -COLUMNS);
                else if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(right)) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Center tiles
            } else {
                if (direction.equals(up)) swap(context, position, -COLUMNS);
                else if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(right)) swap(context, position, 1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
            }


            // Upper-left-corner tile`
       /*     if (position == 0) {
                if (direction.equals(right) && tileList[1].equals("8")) swap(context, position, 1);
                else if (direction.equals(down) && tileList[3].equals("8"))
                    swap(context, position, COLUMNS);
               // else //Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                // Upper-center tiles
            } else if (position > 0 && position < COLUMNS - 1) {
                if (direction.equals(left) && tileList[0].equals("8")) swap(context, position, -1);
                else if (direction.equals(down) && tileList[4].equals("8"))
                    swap(context, position, COLUMNS);
                else if (direction.equals(right) && tileList[2].equals("8"))
                    swap(context, position, 1);
               /// else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-right-corner tile
            } else if (position == COLUMNS - 1) {
                if (direction.equals(left) && tileList[1].equals("8")) swap(context, position, -1);
                else if (direction.equals(down) && tileList[5].equals("8"))
                    swap(context, position, COLUMNS);
                //else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Left-side tiles
            } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                    position % COLUMNS == 0) {
                if (direction.equals(up) && tileList[0].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(right) && tileList[4].equals("8"))
                    swap(context, position, 1);
                else if (direction.equals(down) && tileList[6].equals("8"))
                    swap(context, position, COLUMNS);
               // else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Right-side AND bottom-right-corner tiles
            } else if (position == COLUMNS * 2 - 1) {
                if (direction.equals(up) && (tileList[2].equals("8") || tileList[5].equals("2")))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(left) && tileList[4].equals("8"))
                    swap(context, position, -1);
                    // else if (direction.equals(left) &&  tileList[7].equals("2")  ) swap(context, position, -1);
                else if (direction.equals(down) && tileList[8].equals("8")) {

                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                  //  else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                }// else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


                // Bottom-left corner tile
            } else if (position == COLUMNS * 3 - 1) {
                if (direction.equals(up) && tileList[5].equals("8"))
                    swap(context, position, -COLUMNS);
                    //else if (direction.equals(left) && tileList[4].equals("2")) swap(context, position, -1);
                else if (direction.equals(left) && tileList[7].equals("8"))
                    swap(context, position, -1);
            else if (direction.equals(down) && tileList[8].equals("2")) {
                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
              //  else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            }
              //  else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


            } else if (position == DIMENSIONS - COLUMNS) {
                if (direction.equals(up) && tileList[3].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(right) && tileList[7].equals("8"))
                    swap(context, position, 1);
               // else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Bottom-center tiles
            } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
                if (direction.equals(up) && tileList[4].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(left) && tileList[6].equals("8"))
                    swap(context, position, -1);
                else if (direction.equals(right) && tileList[8].equals("8"))
                    swap(context, position, 1);
               //Frt else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Center tiles
            } else {
                if (direction.equals(up) && tileList[1].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(left) && tileList[3].equals("8"))
                    swap(context, position, -1);
                else if (direction.equals(right) && tileList[5].equals("8"))
                    swap(context, position, 1);
                else if (direction.equals(down) && tileList[7].equals("8"))
                    swap(context, position, COLUMNS);
            }*/

        } else if (COLUMNS == 4) {
            if (position == 0) {
                if (direction.equals(right) && tileList[1].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[4].equals("15")) {
                    swap(context, position, 4);
                }
            } else if (position == 1) {
                if (direction.equals(left) && tileList[0].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[5].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[2].equals("15")) {
                    swap(context, position, 1);
                }

            } else if (position == 2) {
                if (direction.equals(left) && tileList[1].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[6].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[3].equals("15")) {
                    swap(context, position, 1);
                }
            } else if (position == 3) {
                if (direction.equals(left) && tileList[2].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[7].equals("15")) {
                    swap(context, position, 4);
                }
            } else if (position == 4) {
                if (direction.equals(up) && tileList[0].equals("15")) {
                    swap(context, position, -4);
                } else if (direction.equals(down) && tileList[8].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[5].equals("15")) {
                    swap(context, position, 1);
                }
            } else if (position == 5) {
                if (direction.equals(left) && tileList[4].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[9].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[6].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[1].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 6) {
                if (direction.equals(left) && tileList[5].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[10].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[7].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[2].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 7) {
                if (direction.equals(left) && tileList[6].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[11].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(up) && tileList[3].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 8) {
                if (direction.equals(up) && tileList[4].equals("15")) {
                    swap(context, position, -4);
                } else if (direction.equals(down) && tileList[12].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[9].equals("15")) {
                    swap(context, position, 1);
                }
            } else if (position == 9) {
                if (direction.equals(left) && tileList[8].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[12 + 1].equals("15")) {
                    swap(context, position, 3 + 1);
                } else if (direction.equals(right) && tileList[10].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[5].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 10) {
                if (direction.equals(left) && tileList[9].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[14].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(right) && tileList[11].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[6].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 11) {
                if (direction.equals(left) && tileList[10].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[15].equals("15")) {
                    swap(context, position, 4);
                } else if (direction.equals(up) && tileList[7].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 12) {
                if (direction.equals(right) && tileList[13].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[8].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 13) {
                if (direction.equals(left) && tileList[12].equals("15")) {
                    swap(context, position, -1);


                } else if (direction.equals(right) && tileList[14].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[9].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 14) {
                if (direction.equals(left) && tileList[13].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[15].equals("15")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[10].equals("15")) {
                    swap(context, position, -4);
                }
            } else if (position == 15) {
                if (direction.equals(left) && tileList[14].equals("15")) {
                    swap(context, position, -1);
                } else if (direction.equals(up) && tileList[11].equals("15")) {
                    swap(context, position, -4);
                }
            }

        } else if (COLUMNS == 5) {

            if (position == 0) {
                if (direction.equals(right) && tileList[1].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[5].equals("24")) {
                    swap(context, position, 5);


                }
            } else if (position == 1) {
                if (direction.equals(left) && tileList[0].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[2].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[6].equals("24")) {
                    swap(context, position, 5);
                }
            } else if (position == 2) {
                if (direction.equals(left) && tileList[1].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[3].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[7].equals("24")) {
                    swap(context, position, 5);
                }
            } else if (position == 3) {
                if (direction.equals(left) && tileList[2].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[4].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[8].equals("24")) {
                    swap(context, position, 5);
                }
            } else if (position == 4) {
                if (direction.equals(left) && tileList[3].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[9].equals("24")) {
                    swap(context, position, 5);
                }
            } else if (position == 5) {
                if (direction.equals(right) && tileList[6].equals("24")) {
                    swap(context, position, 1);


                } else if (direction.equals(up) && tileList[0].equals("24")) {
                    swap(context, position, -5);
                } else if (direction.equals(down) && tileList[10].equals("24")) {
                    swap(context, position, 5);
                }
            } else if (position == 6) {
                if (direction.equals(left) && tileList[5].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[7].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[11].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[1].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 7) {
                if (direction.equals(left) && tileList[6].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[8].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[12].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[2].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 8) {
                if (direction.equals(left) && tileList[7].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[9].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[13].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[3].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 9) {
                if (direction.equals(left) && tileList[8].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[14].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[4].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 10) {
                if (direction.equals(right) && tileList[11].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[15].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[5].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 11) {
                if (direction.equals(left) && tileList[10].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[12].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[16].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[6].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 12) {
                if (direction.equals(left) && tileList[11].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[13].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[17].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[7].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 13) {
                if (direction.equals(left) && tileList[12].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[14].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[18].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[8].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 14) {
                if (direction.equals(left) && tileList[13].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[19].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[9].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 15) {
                if (direction.equals(down) && tileList[20].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[10].equals("24")) {
                    swap(context, position, -5);
                } else if (direction.equals(right) && tileList[16].equals("24")) {
                    swap(context, position, 1);
                }
            } else if (position == 16) {
                if (direction.equals(left) && tileList[15].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[17].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[21].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[11].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 17) {
                if (direction.equals(left) && tileList[16].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[18].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[22].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[12].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 18) {
                if (direction.equals(left) && tileList[17].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[19].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[23].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[13].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 19) {
                if (direction.equals(left) && tileList[18].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[24].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[14].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 20) {
                if (direction.equals(right) && tileList[21].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[15].equals("24")) {
                    swap(context, position, -5);
                }

            } else if (position == 21) {
                if (direction.equals(left) && tileList[20].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[22].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[16].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 22) {
                if (direction.equals(left) && tileList[21].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[23].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[17].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 23) {
                if (direction.equals(left) && tileList[22].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[24].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[18].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 24) {
                if (direction.equals(left) && tileList[23].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(up) && tileList[19].equals("24")) {
                    swap(context, position, -5);
                }
            }

        }
    }


    private boolean isSolved() {
        boolean solved = false;


        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;


    }


    public static Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timeView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds));

            handler.postDelayed(this, 0);
        }

    };


    public static void start() {
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    public static void push() {
        TimeBuff += MillisecondTime;
        handler.removeCallbacks(runnable);
    }

    public static void reset() {
        MillisecondTime = 0L;
        StartTime = 0L;
        TimeBuff = 0L;
        UpdateTime = 0L;
        Seconds = 0;
        Minutes = 0;
        MilliSeconds = 0;

        timeView.setText("00:00");


    }

    protected void createCustomDialog(int drawable) {
        LayoutInflater inflater = LayoutInflater.from(GameActivity.this);
        View customDialog = inflater.inflate(R.layout.showimage, null);
        ImageView img = (ImageView) customDialog.findViewById(R.id.setimg);
        img.setImageResource(drawable);
        dialog = new AlertDialog.Builder(GameActivity.this).create();
        dialog.setView(customDialog);
        dialog.show();

    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder alrt = new AlertDialog.Builder(GameActivity.this, R.style.MyDialogThem);
        alrt.setIcon(R.drawable.logo_avangers);
        alrt.setTitle("Exit ??");
        alrt.setCancelable(false);
        push();
        alrt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameActivity.super.onBackPressed();
                BackgroundMusic();
                dialog.dismiss();
                reset();
            }
        });


        alrt.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                start();
            }
        });
        alrt.show();


    }
}