package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Jogging extends AppCompatActivity {
    TextView startTime,endTime,totalDuration;
    Button start,stop;
    String start_time;
    String end_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogging);

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        totalDuration = findViewById(R.id.totalDuration);
        start = findViewById(R.id.startTrip);
        stop = findViewById(R.id.stopTrip);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                startTime.setText(start_time);

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end_time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                endTime.setText(end_time);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                // Parsing the Time Period
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = simpleDateFormat.parse(start_time);
                    date2 = simpleDateFormat.parse(end_time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Calculating the difference in milliseconds
                long differenceInMilliSeconds
                        = Math.abs(date2.getTime() - date1.getTime());

                // Calculating the difference in Hours
                long differenceInHours
                        = (differenceInMilliSeconds / (60 * 60 * 1000))
                        % 24;

                // Calculating the difference in Minutes
                long differenceInMinutes
                        = (differenceInMilliSeconds / (60 * 1000)) % 60;

                // Calculating the difference in Seconds
                long differenceInSeconds
                        = (differenceInMilliSeconds / 1000) % 60;

                totalDuration.setText(differenceInHours+":"+differenceInMinutes+":"+differenceInSeconds);
            }
        });
    }
}