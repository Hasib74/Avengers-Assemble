/*
package com;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasib.a2dcomicspuzzlegame.CustomAdapter;
import com.example.hasib.a2dcomicspuzzlegame.GestureDetectGridView;
import com.example.hasib.a2dcomicspuzzlegame.NextStageOper;
import com.example.hasib.a2dcomicspuzzlegame.R;

import java.util.ArrayList;
import java.util.Random;

public class textcode {
}
package com.example.hasib.a2dcomicspuzzlegame;

        import android.app.Activity;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Point;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.SystemClock;
        import android.preference.PreferenceManager;
        import android.provider.ContactsContract;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.Display;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.ViewTreeObserver;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Map;
        import java.util.Random;
        import java.util.Set;
        import java.util.logging.Handler;
        import java.util.logging.LogRecord;


public class GameActivity extends AppCompatActivity {

    public boolean solveGame = false;
    public static int[] getPicture;
    public static Boolean isPlay;
    // public static TextView textView;
    Button Save;
    // public static TextView highScore;
    public static String scoreTime;
    public static Double temLowpScore = 0.0;
    public static Double temHighpScore = 999.0;
    public static TextView timeView;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    // android.os.Handler handler;

    //Context Mcontext=GameActivity.this;
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
    private int seconds=0;
    private boolean startRun=true;

    TextView textView ;
    Button btnStart;

    // Button start, pause, reset, lap ;

    static long MillisecondTime;
    static long StartTime;
    static long TimeBuff;
    static long UpdateTime = 0L ;

    public static android.os.Handler handler=new android.os.Handler();

    static int Seconds;
    static int Minutes;
    static int MilliSeconds ;
    Context context;
    static TextView highTime;
    public   static  String imgreferance;
    ImageButton imageButton;
    String txt;


    //boolean run;



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Activity
        sharedPreferences=this.getSharedPreferences("SP",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        unicpic=getIntent().getIntExtra("picture",0);




        //Timer(startRun);
        // highTime=(TextView)findViewById(R.id.showhight);
        timeView=(TextView)findViewById(R.id.timeview);

        // unicpic = getIntent().getIntExtra("picture", R.mipmap.ic_launcher);
        getPicture = getIntent().getIntArrayExtra("Game1");
        number = getPicture.length;

        //textView = (TextView)findViewById(R.id.textView);
        setPicture = (ImageView)findViewById(R.id.picture);
        setPicture.setImageResource(unicpic);



        imgreferance=getIntent().getStringExtra("score");
        txt=getIntent().getStringExtra("easy");
        Toast.makeText(getApplicationContext(),"Picture Name :-"+imgreferance,Toast.LENGTH_SHORT).show();


        //imageReferance();


        setPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  im(unicpic);
                createCustomDialog(unicpic);

            }
        });

        // start();
        // Save = (Button) findViewById(R.id.Save);
        //highScore=(TextView)findViewById(R.id.highScore);

        COLUMNS = getIntent().getIntExtra("COLUMS", 1);
        DIMENSIONS = COLUMNS * COLUMNS;

        // Toast.makeText(GameActivity.this,"Dimention ==="+DIMENSIONS,Toast.LENGTH_SHORT).show();

        //   Toast.makeText(GameActivity.this,"Colums"+COLUMNS,Toast.LENGTH_SHORT).show();

        isSolveGame = getIntent().getBooleanExtra("UNIQID", false);

        init();                                             //call init function

        scramble();                                        //call scramble function

        setDimensions();
        isPlay = true;



        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);




    }




  private void imageReferance() {

        if (imgreferance.equals("halk")){
            highTime.setText(sharedPreferences.getString("e1","00:00"));
        }else if (imgreferance.equals("hawkey")){
            highTime.setText(sharedPreferences.getString("e2","00:00"));
        }else if (imgreferance.equals("captainAmrica")){
            highTime.setText(sharedPreferences.getString("e3","00:00"));
        }else if (imgreferance.equals("IronMan")){
            highTime.setText(sharedPreferences.getString("e4","00:00"));
        }else if (imgreferance.equals("thor")){
            highTime.setText(sharedPreferences.getString("e5","00:00"));
        }else if (imgreferance.equals("blackWidow")){
            highTime.setText(sharedPreferences.getString("e6","00:00"));
        }else if (imgreferance.equals("supperMan")){
            highTime.setText(sharedPreferences.getString("h1","00:00"));
        }else if (imgreferance.equals("blackPanther")){
            highTime.setText(sharedPreferences.getString("h2","00:00"));
        }else if (imgreferance.equals("quickSilver")){
            highTime.setText(sharedPreferences.getString("h3","00:00"));
        }else if (imgreferance.equals("scarl")){
            highTime.setText(sharedPreferences.getString("h4","00:00"));
        }else if (imgreferance.equals("wasp")){
            highTime.setText(sharedPreferences.getString("h5","00:00"));
        }else if (imgreferance.equals("antMan")){
            highTime.setText(sharedPreferences.getString("m1","00:00"));
        }else if (imgreferance.equals("doctoreStrane")){
            highTime.setText(sharedPreferences.getString("m2","00:00"));
        }else if (imgreferance.equals("falon")){
            highTime.setText(sharedPreferences.getString("m3","00:00"));
        }else if (imgreferance.equals("vison")){
            highTime.setText(sharedPreferences.getString("m4","00:00"));
        }
        else if (imgreferance.equals("warmansing")){
            highTime.setText(sharedPreferences.getString("m5","00:00"));
        }
    }



    private static void setScore(String time) {
        // imgreferance=getIntent().getStringExtra("img1");
        if (imgreferance.equals("halk")){
            //highTime.setText(time);
            editor.putString("e1",time);
            editor.commit();
        }else if (imgreferance.equals("hawkey")){
            //highTime.setText(time);
            editor.putString("e2",time);
            editor.commit();
        }else if (imgreferance.equals("captainAmrica")){
            // highTime.setText(time);
            editor.putString("e3",time);
            editor.commit();
        }else if (imgreferance.equals("IronMan")){
            //highTime.setText(time);
            editor.putString("e4",time);
            editor.commit();
        }else if (imgreferance.equals("thor")){
            // highTime.setText(time);
            editor.putString("e5",time);
            editor.commit();
        }else if (imgreferance.equals("blackWidow")){
            // highTime.setText(time);
            editor.putString("e6",time);
            editor.commit();
        }else if (imgreferance.equals("supperMan")){
            editor.putString("h1",time);
            editor.commit();
        }else if (imgreferance.equals("blackPanther")){
            editor.putString("h2",time);
            editor.commit();
        }else if (imgreferance.equals("quickSilver")){
            editor.putString("h3",time);
            editor.commit();
        }else if (imgreferance.equals("scarl")){
            editor.putString("h4",time);
            editor.commit();
        }else if (imgreferance.equals("wasp")){
            editor.putString("h5",time);
            editor.commit();
        }else if (imgreferance.equals("antMan")){
            editor.putString("m1",time);
            editor.commit();
        }else if (imgreferance.equals("doctoreStrane")){
            editor.putString("m2",time);
            editor.commit();
        }else if (imgreferance.equals("falon")){
            editor.putString("m3",time);
            editor.commit();
        }else if (imgreferance.equals("vison")){
            editor.putString("m4",time);
            editor.commit();
        }else if (imgreferance.equals("warmansing")){
            editor.putString("m5",time);
            editor.commit();
        }
    }

    public GameActivity() {


    }

    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid); //initialisd the GestureDetectGridView which is in in activity
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


//        Toast.makeText(GameActivity.this,"Number="+number,Toast.LENGTH_SHORT).show();
        //ArrayList<Button> buttons = new ArrayList<>();
        //   Button button;
        //  button = new Button(context);

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



        isPlay = true;


        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));

    }


    private void swap(final Context context, int currentPosition, int swap) {
        // isPlay=true;

        //NextStageOper nextStageOper = new NextStageOper();

        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);
        // run=true;

        if (isSolved()) {

            //   String time=this.timeView.getText().toString();
            //  startRun=false;
            //  context.this.timeView.setText("00:00:00");
            //push();
            // android.os.Handler handler1=new android.os.Handler();

            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();



            // Toast.makeText(context, "Time :-"+time, Toast.LENGTH_SHORT).show();

            // android.os.Handler handler=new android.os.Handler();
 TimeBuff += MillisecondTime;
            handler.removeCallbacks(runnable);


            String text=timeView.getText().toString();

            Double presenttime=ConvertTime(text);

            Double preViousTime=ConvertTime(highTime.getText().toString());
            if (preViousTime==00.00 || preViousTime>presenttime){
                highTime.setText(text);

                String time=highTime.getText().toString();
                setScore(time);

            }
            // highTime.setText(text);


            Toast.makeText(context,"Time :-"+text,Toast.LENGTH_SHORT).show();
            push();

            // Toast.makeText(context,"Double Time :-"+presenttime,Toast.LENGTH_LONG).show();



            reset();
            //  timeView=(TextView)findViewById(R.id.timeview);

            Log.e("PUSH...............:=","Successfully push");


            solveDialog(context);


            SharedPreferences sharedPreferences=context.getSharedPreferences("SP",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();

            if (COLUMNS == 4) {
                SharedPreferences sp=context.getSharedPreferences("M",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();


                if (imgreferance.equals("antMan")){
                    ed.putBoolean("m1",true);
                    ed.commit();

                }else if

                        (imgreferance.equals("doctoreStrane")) {

                    ed.putBoolean("m2",true);
                    ed.commit();


                } else if (imgreferance.equals("falon")) {
                    ed.putBoolean("m3",true);
                    ed.commit();
                    //solveDialog(context,unicpic);
                } else if (imgreferance.equals("vison")) {
                    ed.putBoolean("m4",true);
                    ed.commit();
                    // solveDialog(context,unicpic);
                } else if (imgreferance.equals("warmansing")) {

                    ed.putBoolean("m4",true);
                    ed.commit();
                    // solveDialog(context,unicpic);

                }
            } else if (COLUMNS == 3) {
                SharedPreferences sp=context.getSharedPreferences("test",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                //  SharedPreferences sharedPreferences=GameActivity.this.getSharedPreferences("GameDB",MODE_PRIVATE);
                if (imgreferance.equals("halk")){
                    // NextStageOper.easyStage2 = true;
                    ed.putBoolean("solve1",true);
                    ed.commit();
                }


                else if (imgreferance.equals("hawkey")) {



                    //  Toast.makeText(context, " Mediam easy stage 2  ", Toast.LENGTH_SHORT).show();

                    // NextStageOper.easyStage2 = true;

                    // solveDialog(context,unicpic);

                    ed.putBoolean("solve2",true);
                    ed.commit();


                } else if (imgreferance.equals("captainAmrica")) {
                    // Toast.makeText(context, " Mediam easy stage 3  ", Toast.LENGTH_SHORT).show();

                    // NextStageOper.easyStage3 = true;
                    ed.putBoolean("solve3",true);
                    ed.commit();


                    //solveDialog(context,unicpic);


                } else if (imgreferance.equals("IronMan")) {
                    //  Toast.makeText(context, " Mediam easy stage 4  ", Toast.LENGTH_SHORT).show();


                    ed.putBoolean("solve4",true);
                    ed.commit();



                    //solveDialog(context,unicpic);


                } else if (imgreferance.equals("thor")) {
                    // Toast.makeText(context, " Mediam easy stage 5  ", Toast.LENGTH_SHORT).show();


                    ed.putBoolean("solve5",true);
                    ed.commit();



                    //solveDialog(context,unicpic);


                    // int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);

                } else if (imgreferance.equals("blackWidow")) {
                    // Toast.makeText(context, " Mediam easy stage 5  ", Toast.LENGTH_SHORT).show();


                    ed.putBoolean("solve6",true);
                    ed.commit();



                    // solveDialog(context,unicpic);


                    // int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);

                }


            } else if (COLUMNS == 5) {


                SharedPreferences sp=context.getSharedPreferences("H",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();


                if (imgreferance.equals("supperMan")) {


                    ed.putBoolean("h1", NextStageOper.hard2);
                    ed.commit();
                    //showDialog(context);
                } else if (imgreferance.equals("blackPanther")) {
                    ed.putBoolean("h2",NextStageOper.hard2);
                    ed.commit();
                } else if (imgreferance.equals("quickSilver")) {
                    ed.putBoolean("h3",NextStageOper.hard2);
                    ed.commit();
                    //showDialog(context);
                } else if (imgreferance.equals("scarl")) {


                    ed.putBoolean("h3",NextStageOper.hard2);
                    ed.commit();

                    // showDialog(context);
                }
            }

            //push();


        }


        mGridView.enable(true);


        //  Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    private static  void solveDialog(final Context context) {


 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Your Title");

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        mGridView.gameActivity.finish();
                        Intent in=new Intent(context,EasyClass.class);
                        context.startActivity(in);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


  final Dialog dialog=new Dialog(context);
           Button RePlay,NewGame;
           ImageView setImg;

           //
           // dialog.setContentView(R.layout.custom_dialog);

           LayoutInflater inflater=LayoutInflater.from(context);
           View v=inflater.inflate(R.layout.custom_dialog,null);


           RePlay=(Button)v.findViewById(R.id.rePlay);
           NewGame=(Button)v.findViewById(R.id.newGame);
           setImg=(ImageView)v.findViewById(R.id.img);
        dialog.setContentView(v);

      setImg.setImageResource(picture);

      RePlay.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   dialog.dismiss();
               }
           });
       NewGame.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   dialog.dismiss();
                   context.startActivity(new Intent(context,EasyClass.class));
               }
       });




      dialog.show();



        //  int picture=getIntent().getIntExtra("picture", R.mipmap.ic_launcher);

        LayoutInflater inflater = LayoutInflater.from(context);
        View customDialog = inflater.inflate(R.layout.show_picture, null);
        //((TextView)customDialog.findViewById(R.id.genericErrorDialogTitle)).setText(title);
        // ((TextView)customDialog.findViewById(R.id.genericErrorDialogMessage)).setText(message);
        ((ImageView)customDialog.findViewById(R.id.showPic)).setImageResource(unicpic);
        //dialog.getWindow().setGravity(0);

        dialog = new AlertDialog.Builder(context).create();
        dialog.setView(customDialog);
        // dialog.setButton(getText(R.string.listaBusquedasGuardadasNoResultDialogButton), onClickListener);
        dialog.show();
        dialog.getWindow().setLayout(600, 610);
        //reset();







    }


    public void moveTiles(Context context, String direction, int position) {
        isPlay = true;


        if (COLUMNS == 3) {



            if (position == 0) {

                if (direction.equals(right)  ) swap(context, position, 1);
                else if (direction.equals(down) ) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-center tiles
            } else if (position > 0 && position < COLUMNS - 1) {
                if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-right-corner tile
            } else if (position == COLUMNS - 1) {
                if (direction.equals(left)  ) swap(context, position, -1);
                else if (direction.equals(down) ) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Left-side tiles
            } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                    position % COLUMNS == 0) {
                if (direction.equals(up)  ) swap(context, position, -COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Right-side AND bottom-right-corner tiles
            } else if (position == COLUMNS * 2 - 1) {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(left)  ) swap(context, position, -1);
                    // else if (direction.equals(left) &&  tileList[7].equals("2")  ) swap(context, position, -1);
                else if (direction.equals(down) ) {

                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


                // Bottom-left corner tile
            }

            else if ( position == COLUMNS * 3 - 1) {
                if (direction.equals(up) )
                    swap(context, position, -COLUMNS);
                    //else if (direction.equals(left) && tileList[4].equals("2")) swap(context, position, -1);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(down) && tileList[8].equals("2")) {
                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


            }

            else if (position == DIMENSIONS - COLUMNS) {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Bottom-center tiles
            } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
                if (direction.equals(up )) swap(context, position, -COLUMNS);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Center tiles
            } else {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(right) ) swap(context, position, 1);
                else if(direction.equals(down)) swap(context, position, COLUMNS);
            }

            // Upper-left-corner tile`

 if (position == 0) {

                if (direction.equals(right) && tileList[1].equals("8")) swap(context, position, 1);
                else if (direction.equals(down) && tileList[3].equals("8"))
                    swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-center tiles
            } else if (position > 0 && position < COLUMNS - 1) {
                if (direction.equals(left) && tileList[0].equals("8")) swap(context, position, -1);
                else if (direction.equals(down) && tileList[4].equals("8"))
                    swap(context, position, COLUMNS);
                else if (direction.equals(right) && tileList[2].equals("8"))
                    swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-right-corner tile
            } else if (position == COLUMNS - 1) {
                if (direction.equals(left) && tileList[1].equals("8")) swap(context, position, -1);
                else if (direction.equals(down) && tileList[5].equals("8"))
                    swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Left-side tiles
            } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                    position % COLUMNS == 0) {
                if (direction.equals(up) && tileList[0].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(right) && tileList[4].equals("8"))
                    swap(context, position, 1);
                else if (direction.equals(down) && tileList[6].equals("8"))
                    swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

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
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


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
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            }
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


            } else if (position == DIMENSIONS - COLUMNS) {
                if (direction.equals(up) && tileList[3].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(right) && tileList[7].equals("8"))
                    swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Bottom-center tiles
            } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
                if (direction.equals(up) && tileList[4].equals("8"))
                    swap(context, position, -COLUMNS);
                else if (direction.equals(left) && tileList[6].equals("8"))
                    swap(context, position, -1);
                else if (direction.equals(right) && tileList[8].equals("8"))
                    swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

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
            }


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
                if (direction.equals(up) && tileList[0].equals("15")) {
                    swap(context, position, -8);
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


            if (position == 0) {

                if (direction.equals(right)  ) swap(context, position, 1);
                else if (direction.equals(down) ) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-center tiles
            } else if (position > 0 && position < COLUMNS - 1) {
                if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-right-corner tile
            } else if (position == COLUMNS - 1) {
                if (direction.equals(left)  ) swap(context, position, -1);
                else if (direction.equals(down) ) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Left-side tiles
            } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                    position % COLUMNS == 0) {
                if (direction.equals(up)  ) swap(context, position, -COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Right-side AND bottom-right-corner tiles
            } else if (position == COLUMNS * 2 - 1) {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(left)  ) swap(context, position, -1);
                    // else if (direction.equals(left) &&  tileList[7].equals("2")  ) swap(context, position, -1);
                else if (direction.equals(down) ) {

                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


                // Bottom-left corner tile
            }

            else if ( position == COLUMNS * 3 - 1) {
                if (direction.equals(up) )
                    swap(context, position, -COLUMNS);
                    //else if (direction.equals(left) && tileList[4].equals("2")) swap(context, position, -1);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(down) && tileList[8].equals("2")) {
                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


            }

            else if (position == DIMENSIONS - COLUMNS) {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Bottom-center tiles
            } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
                if (direction.equals(up )) swap(context, position, -COLUMNS);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Center tiles
            } else {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(right) ) swap(context, position, 1);
                else if(direction.equals(down)) swap(context, position, COLUMNS);
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
            } else if (position == 10)

            {
                if (direction.equals(right) && tileList[11].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[15].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[5].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 11)

            {
                if (direction.equals(left) && tileList[10].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[12].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[16].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[6].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 12)

            {
                if (direction.equals(left) && tileList[11].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[13].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[17].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[7].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 13)

            {
                if (direction.equals(left) && tileList[12].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[14].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[18].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[8].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 14)

            {
                if (direction.equals(left) && tileList[13].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[19].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[9].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 15)

            {
                if (direction.equals(down) && tileList[20].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[10].equals("24")) {
                    swap(context, position, -5);
                } else if (direction.equals(right) && tileList[16].equals("24")) {
                    swap(context, position, 1);
                }
            } else if (position == 16)

            {
                if (direction.equals(left) && tileList[15].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[17].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[21].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[11].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 17)

            {
                if (direction.equals(left) && tileList[16].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[18].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[22].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[12].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 18)

            {
                if (direction.equals(left) && tileList[17].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[19].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(down) && tileList[23].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[13].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 19)

            {
                if (direction.equals(left) && tileList[18].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(down) && tileList[23].equals("24")) {
                    swap(context, position, 5);
                } else if (direction.equals(up) && tileList[14].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 20)

            {
                if (direction.equals(right) && tileList[21].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[15].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 21)

            {
                if (direction.equals(left) && tileList[20].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[22].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[16].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 22)

            {
                if (direction.equals(left) && tileList[21].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[23].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[17].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 23)

            {
                if (direction.equals(left) && tileList[22].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(right) && tileList[24].equals("24")) {
                    swap(context, position, 1);
                } else if (direction.equals(up) && tileList[18].equals("24")) {
                    swap(context, position, -5);
                }
            } else if (position == 24)

            {
                if (direction.equals(left) && tileList[23].equals("24")) {
                    swap(context, position, -1);
                } else if (direction.equals(up) && tileList[19].equals("24")) {
                    swap(context, position, -5);
                }
            }



            if (position == 0) {

                if (direction.equals(right)  ) swap(context, position, 1);
                else if (direction.equals(down) ) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-center tiles
            } else if (position > 0 && position < COLUMNS - 1) {
                if (direction.equals(left)) swap(context, position, -1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Upper-right-corner tile
            } else if (position == COLUMNS - 1) {
                if (direction.equals(left)  ) swap(context, position, -1);
                else if (direction.equals(down) ) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Left-side tiles
            } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                    position % COLUMNS == 0) {
                if (direction.equals(up)  ) swap(context, position, -COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else if (direction.equals(down)) swap(context, position, COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Right-side AND bottom-right-corner tiles
            } else if (position == COLUMNS * 2 - 1) {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(left)  ) swap(context, position, -1);
                    // else if (direction.equals(left) &&  tileList[7].equals("2")  ) swap(context, position, -1);
                else if (direction.equals(down) ) {

                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


                // Bottom-left corner tile
            }

            else if ( position == COLUMNS * 3 - 1) {
                if (direction.equals(up) )
                    swap(context, position, -COLUMNS);
                    //else if (direction.equals(left) && tileList[4].equals("2")) swap(context, position, -1);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(down) && tileList[8].equals("2")) {
                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                            COLUMNS);
                    else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();


            }

            else if (position == DIMENSIONS - COLUMNS) {
                if (direction.equals(up) ) swap(context, position, -COLUMNS);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Bottom-center tiles
            } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
                if (direction.equals(up )) swap(context, position, -COLUMNS);
                else if (direction.equals(left) ) swap(context, position, -1);
                else if (direction.equals(right) ) swap(context, position, 1);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

                // Center tiles
            } else {

                if (direction.equals(up) ) swap(context, position, -COLUMNS);

                else if (direction.equals(left) ) swap(context, position, -1);

                else if (direction.equals(right) ) swap(context, position, 1);

                else if(direction.equals(down)) swap(context, position, COLUMNS);

            }

        }
    }









    private  boolean isSolved() {
        boolean solved = false;

        // timeView.setText("00.00.00");



        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
 Intent in=new Intent(getApplicationContext(),EasyClass.class);
        startActivity(in);




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



public void SAVE(View v){

        if(isPlay){
            push();
            isPlay=false;
            Save.setText("START");
            mGridView.enable(false);

        }else {
            start();
            isPlay=false;
            Save.setText("STOP");
            mGridView.enable(true);

        }


    }

    public static void   start(){
 StartTime = SystemClock.uptimeMillis();
    timeView.setText(String.valueOf(StartTime));
    timeView.postDelayed(runnable, 0);

        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }
    public static void push(){
        TimeBuff += MillisecondTime;
        // android.os.Handler handler=new android.os.Handler();
        handler.removeCallbacks(runnable);
    }
    public static   void reset(){
        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;

        timeView.setText("00:00");


    }

    private static   Double ConvertTime(String s) {
        String[] hourMin = s.split(":");
        Double mins = Double.parseDouble(hourMin[0]);
        Double second = Double.parseDouble(hourMin[1]);
        Double hoursInMins = mins * 60;
        return hoursInMins + second;
    }



   public  String covertTimeToString(Double aDuration) {
        String result = "";

        double hours = 0;
        double minutes = 0;

        hours = aDuration / 3600;
        minutes = (aDuration - hours * 3600) / 60;
       // seconds = (aDuration - (hours * 3600 + minutes * 60));

        result = String.format("%02f : %02f", hours, minutes);
        return result;
    }


    public   int rating(int time){

        int ratingRate;
        if (time<100){
            ratingRate=5;
        }else if(100<time &&time<200){
            ratingRate=4;
        }else if(200<time &&time<500){
            ratingRate=3;
        }else if(500<time&&time<1000){
            ratingRate=2;

        }else if(1000<time && time<1500){
            ratingRate=1;
        }else {
            ratingRate=0;
        }

        return  ratingRate;

    }






  public  void ShowAlartDialog(final int rat){


      final AlertDialog.Builder popDialog = new AlertDialog.Builder(GameActivity.this);
      final RatingBar rating = new RatingBar(GameActivity.this);
      rating.setMax(5);
      rating.getNumStars();
      rating.setRating(rat);
      rating.setNumStars(rat);



      popDialog.setIcon(android.R.drawable.btn_star_big_on);
      popDialog.setTitle("You Have Got  "+rat);
      popDialog.setView(rating);

      popDialog.setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              Intent intent=new Intent(context ,Selection.class);
              intent.putExtra("passRating",rat);
              startActivity(intent);
          }
      });

      popDialog.show();
   }




    public void im(int im){
        // showDialog();
        Dialog dialog = new Dialog(GameActivity.this);
        //   LayoutInflater factory = LayoutInflater.from(GameActivity.this);


        dialog.setContentView(R.layout.show_picture);
        ImageView showImage=(ImageView)findViewById(R.id.showPic);
        // showImage.setImageResource(unicpic);

        showImage.setImageResource(R.drawable.ant1);


        dialog.getWindow().setLayout(600, 500);


        // dialog.create();
        dialog.show();

    }



    protected void createCustomDialog(int drawable){
        LayoutInflater inflater = LayoutInflater.from(GameActivity.this);
        View customDialog = inflater.inflate(R.layout.showimage, null);
        //((TextView)customDialog.findViewById(R.id.genericErrorDialogTitle)).setText(title);
        // ((TextView)customDialog.findViewById(R.id.genericErrorDialogMessage)).setText(message);

        ImageView img=(ImageView)customDialog.findViewById(R.id.setimg);
        img.setImageResource(drawable);
        //dialog.getWindow().setGravity(0);

        dialog = new AlertDialog.Builder(GameActivity.this).create();
        dialog.setView(customDialog);
        // dialog.setButton(getText(R.string.listaBusquedasGuardadasNoResultDialogButton), onClickListener);


        dialog.show();


 WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = 150;
        lp.height = 500;
        lp.x=-100;
        lp.y=-200;
        dialog.getWindow().setAttributes(lp);

    }





  private void swap(final Context context, int currentPosition, int swap) {
        // isPlay=true;

        //NextStageOper nextStageOper = new NextStageOper();

        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);
        if (isSolved()) {


            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();

            showDialog(context);

        }

  }



    //  Attempt to invoke virtual method 'android.app.ActivityThread$ApplicationThread android.app.ActivityThread.getApplicationThread()' on a null object reference



private void Timer(final boolean run){
    timeView =(TextView)findViewById(R.id.timeview);
     handler=new android.os.Handler();
    handler.post(new Runnable() {
        @Override
        public void run() {
            int hours=seconds/3600;
            int minutes=(seconds%3600)/60;
            int secs=seconds%60;
            String time = String.format("%d:%02d:%02d", hours, minutes, secs);
            timeView.setText(time);
            if (run){
                seconds++;
            }else {
                timeView.setText("00:00:00");
            }
            handler.postDelayed(this,100);
        }
    });
}






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //  onStop();
    }

}







*/
