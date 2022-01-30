package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static java.security.AccessController.getContext;

public class WorkOutLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out_log);

        /*DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetHistory();
        Log.i("WorkoutLog",userList.toString());
        ListView lv = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(WorkOutLog.this, userList, R.layout.workout_item,new String[]{"Date","Time","Jumping Jack","Sit ups","Push ups", "Squats"}, new int[]{R.id.date, R.id.time, R.id.jumpingjack,R.id.situps,R.id.pushups,R.id.squats});
        lv.setAdapter(adapter);*/

        DbHandler db = new DbHandler(WorkOutLog.this);
        RecyclerView list = findViewById(R.id.workoutList);
        ArrayList<Workout> workoutList = db.getAllWorkouts();
        Log.i("Workout",workoutList.toString());
        db.close();

        CustomWorkoutAdapter adapter = new CustomWorkoutAdapter(workoutList, WorkOutLog.this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(WorkOutLog.this));


    }

}