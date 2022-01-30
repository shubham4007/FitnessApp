package com.example.fitnessapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandlerWorkout extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String ID = "id";
    private static final String DB_NAME = "usersdb";
    private static final String WORKOUT_LOG = "workout_log";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String JUMPING_JACK = "JumpingJack";
    private static final String SIT_UPS = "SitUps";
    private static final String PUSH_UPS = "PushUps";
    private static final String SQUATS = "Squats";

    public DbHandlerWorkout(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + WORKOUT_LOG + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT,"
                + TIME + " TEXT,"
                + JUMPING_JACK + " INTEGER,"
                + SQUATS + " INTEGER,"
                + SIT_UPS + " INTEGER,"
                + PUSH_UPS + " INTEGER"+ ")";
        db.execSQL(CREATE_WORKOUT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUT_LOG);
        // Create tables again
        onCreate(db);
    }

    void insertWorkoutDetails(String date, String time, String jumpingJack, String sitUps, String pushUps,String squats){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(DATE, date);
        cValues.put(TIME, time);
        cValues.put(JUMPING_JACK, jumpingJack);
        cValues.put(SIT_UPS, sitUps);
        cValues.put(PUSH_UPS, pushUps);
        cValues.put(SQUATS, squats);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(WORKOUT_LOG,null, cValues);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetHistory(){
        String email = "EMPTY";
        String password = "EMPTY";
        String age = "EMPTY";
        String height = "EMPTY";
        String weight = "EMPTY";


        System.out.println("ENTERED");
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT date, time,JumpingJack, SitUps, PushUps, Squats   FROM "+ WORKOUT_LOG;
        Cursor cursor = db.rawQuery(query,null);
        System.out.println("Count");
        System.out.println(cursor.getCount());
        System.out.println(cursor);


        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("date",cursor.getString(0));
            email = cursor.getString(1);
            password = cursor.getString(2);
            age = cursor.getString(3);
            height = cursor.getString(4);
            weight = cursor.getString(0);
            user.put("time",cursor.getString(1));
            user.put("JumpingJack",cursor.getString(2));
            user.put("SitUps",cursor.getString(3));
            user.put("PushUps",cursor.getString(4));
            user.put("Squats",cursor.getString(5));
            userList.add(user);
        }
        System.out.println(email);
        System.out.println(password);
        System.out.println(age);
        System.out.println(height);
        System.out.println(weight);
        return  userList;
    }

}
