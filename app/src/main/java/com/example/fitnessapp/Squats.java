package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Squats extends AppCompatActivity {
    Button done;
    String time;
    private int sec = 0;
    private boolean is_running;

    TextView timer;
    Workout workout = new Workout();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squats);

        done = findViewById(R.id.done);
        timer = findViewById(R.id.timer);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.whistle);
        mediaPlayer.start();

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        DbHandler dbHandlerWorkout = new DbHandler(Squats.this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_running = false;
                workout.setSquats(timer.getText().toString());
                workout.setDate(currentDate);
                workout.setTime(currentTime);
                dbHandlerWorkout.insertWorkoutDetails(currentDate,currentTime,workout.getJumpingjack(),workout.getSitups(),workout.getPushups(),workout.getPushups());
                startActivity(new Intent(Squats.this,Dashboard.class));
                finish();

            }
        });
        is_running = true;
        running_Timer();
    }

    private void running_Timer()
    {
        final Handler handle = new Handler();
        handle.post(new Runnable() {
            @Override
            public void run()
            {
                int hrs = sec / 3600;
                int mins = (sec % 3600) / 60;
                int secs = sec % 60;
                String time_t = String .format(Locale.getDefault(), "    %d:%02d:%02d   ", hrs,mins, secs);
                timer.setText(time_t);
                if (is_running) {
                    sec++;
                }
                handle.postDelayed(this, 1000);
            }
        });
    }

}