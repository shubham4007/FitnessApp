package com.example.fitnessapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String USER = "users";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String AGE = "age";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String WORKOUT_LOG = "workout_log";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String JUMPING_JACK = "JumpingJack";
    private static final String SIT_UPS = "SitUps";
    private static final String PUSH_UPS = "PushUps";
    private static final String SQUATS = "Squats";

    public DbHandler(Context context){

        super(context,DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + USER + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EMAIL + " TEXT,"
                + PASSWORD + " TEXT,"
                + AGE + " TEXT,"
                + HEIGHT + " TEXT,"
                + WEIGHT + " TEXT"+ ")";
        db.execSQL(CREATE_USER_TABLE);

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
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUT_LOG);

        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String name, String password, String age, String weight, String height){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(EMAIL, name);
        cValues.put(PASSWORD, password);
        cValues.put(AGE, age);
        cValues.put(HEIGHT, height);
        cValues.put(WEIGHT, weight);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(USER,null, cValues);
        db.close();
    }
    // Get User Details
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetUsers(){



        System.out.println("ENTERED");
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT email, password, age, height, weight FROM "+ USER;
        Cursor cursor = db.rawQuery(query,null);
        System.out.println("Count");
        System.out.println(cursor.getCount());
        System.out.println(cursor);


        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("Email",cursor.getString(0));
            user.put("Password",cursor.getString(1));
            user.put("Age",cursor.getString(2));
            user.put("Height",cursor.getString(3));
            user.put("Weight",cursor.getString(4));
            userList.add(user);
        }

        return  userList;
    }
    // Get User Details based on userid
    public boolean GetUserByUserId(String email){
        String name = "EMPTY";


        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.query(USER, new String[]{EMAIL, PASSWORD, AGE, HEIGHT, WEIGHT}, EMAIL+ "=?",new String[]{String.valueOf(email)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("Email",cursor.getString(0));
            user.put("Password",cursor.getString(1));
            user.put("Age",cursor.getString(2));
            user.put("Height",cursor.getString(3));
            user.put("Weight",cursor.getString(4));
            userList.add(user);
        }
        System.out.println(userList);
        if(userList.isEmpty()){
            return false;
        }

        else
            return true;
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



        System.out.println("ENTERED");
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT date, time,JumpingJack, SitUps, PushUps, Squats   FROM "+ WORKOUT_LOG;
        Cursor cursor = db.rawQuery(query,null);



        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("date",cursor.getString(0));

            user.put("time",cursor.getString(1));
            user.put("JumpingJack",cursor.getString(2));
            user.put("SitUps",cursor.getString(3));
            user.put("PushUps",cursor.getString(4));
            user.put("Squats",cursor.getString(5));
            userList.add(user);
        }

        return  userList;
    }


    public ArrayList<Workout> getAllWorkouts(){
        ArrayList<Workout> workoutList = new ArrayList<>();
        String query = "SELECT * FROM " + WORKOUT_LOG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                workoutList.add(new Workout(

                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
            }while(cursor.moveToNext());
        }
        db.close();
        return workoutList;
    }
}
