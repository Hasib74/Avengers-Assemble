package com.example.hasib.a2dcomicspuzzlegame.DatabaseBoos;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bumptech.glide.request.target.SquaringDrawable;

import java.security.PublicKey;

public class GameDatabase extends SQLiteOpenHelper {
    private static  String DATABSE_NAME="puzzleGameDB";
    private static  int DATABASE_VERSION=6;
    private static String TABLE_NAME="gametable";
    private static String ID="id";
    public static String MODE="gamemode";
    public static String TIME="time";
    public static String NAME="name";
    public static String IMAGENAME="imagename";

    Context context;



    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY,"
            + MODE + " TEXT," + NAME + " TEXT," + IMAGENAME + " TEXT,"+TIME + " TEXT" + ")";




    public GameDatabase(Context context) {

        super(context, DATABSE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public String insertIntoTheDatabse(String mode, String playername, String time, String imageName){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MODE,mode);
        cv.put(TIME,time);
        cv.put(IMAGENAME,imageName);
        cv.put(NAME,playername);

        /*SQLiteDatabase db = this.getWritableDatabase();
             ContentValues values = new ContentValues();
              values.put(COL_NAME, VALUE);
              values.put(COL_NAME, VALUE);


               db.insert(YOUR_TABLE, null, values);*/


        long insert= db.insert(TABLE_NAME,null,cv);



        if (insert>-1){
            return "You successfully insert the data";
        }else{
            return "data not found";
        }



    }

    public Cursor  retiveAllData(String mode){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] colum={MODE,NAME,TIME,IMAGENAME};
        Cursor cursor=db.query(TABLE_NAME,colum,MODE+"='"+mode+"'",null,null,null,null);
        return cursor;
    }


    public  Cursor mintime(String mode){
        SQLiteDatabase db=this.getReadableDatabase();

        String qury ="SELECT * FROM "+TABLE_NAME+" WHERE "+TIME+"=(SELECT MIN("+TIME+") FROM "+TABLE_NAME+" WHERE "+MODE+"="+"'"+mode+"')";

        Cursor c=db.rawQuery(qury,null);
        return c;

    }

    public void  removeData(){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);
    }






}


