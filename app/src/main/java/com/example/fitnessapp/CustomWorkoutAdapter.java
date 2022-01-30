package com.example.fitnessapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomWorkoutAdapter extends RecyclerView.Adapter<CustomWorkoutAdapter.CustomViewHolder>{
    private ArrayList<Workout> workouts;
    private Context context;
    //RequestQueue requestQueue;

    private static final int SEND_SMS_LABEL = 1;

    public CustomWorkoutAdapter(@NonNull ArrayList<Workout> workouts, Context context){
        this.workouts = workouts;
        this.context = context;
        Log.i("Arraylist", workouts.toString());
    }

    @NonNull
    @Override
    public CustomWorkoutAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.workout_item, viewGroup, false);
        final CustomViewHolder customViewHolder = new CustomViewHolder(view);
        customViewHolder.galleryLayout.setVisibility(View.GONE);

        /**
         * Make the entire CardView Clickable
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewHolder.galleryLayout.setVisibility(
                        (customViewHolder.galleryLayout.getVisibility() == View.VISIBLE)
                                ? View.GONE : View.VISIBLE);
            }
        });


        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewHolder, int i) {
        Workout workout = workouts.get(i);
        viewHolder.setIsRecyclable(false);
        Log.i("Custom",i + workouts.get(i).toString());
        viewHolder.time.setText("Time " + workout.getTime());
        viewHolder.date.setText(workout.getDate());
        viewHolder.jumpingJacks.setText(String.format("Jumping Jacks: %s", workout.getJumpingjack()));
        viewHolder.pushup.setText(String.format("Pushups: %s", workout.getPushups()));
        viewHolder.situp.setText(String.format("Situps: %s", workout.getSitups()));
        viewHolder.squats.setText(String.format("Squats: %s", workout.getSquats()));



    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView time;
        protected TextView date;

        protected TextView jumpingJacks;
        protected TextView pushup;
        protected TextView situp;
        protected TextView squats;


        protected LinearLayout galleryLayout;

        public CustomViewHolder(View view){
            super(view);
            this.time  = view.findViewById(R.id.time);
            this.date = view.findViewById(R.id.date);

            this.jumpingJacks = view.findViewById(R.id.jumpingjack);
            this.pushup = view.findViewById(R.id.pushups);
            this.situp = view.findViewById(R.id.situps);
            this.squats = view.findViewById(R.id.squats);


            this.galleryLayout = view.findViewById(R.id.galleryLayout);
        }
    }

}
