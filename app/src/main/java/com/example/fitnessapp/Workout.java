package com.example.fitnessapp;

public class Workout {
    public static String date = "";
    public static String time = "";
    public static String jumpingjack = "" ;
    public static String pushups = "";
    public static String situps = "";
    public static String squats = "";

    public Workout( String string, String string1, String string2, String string3, String string4,String string5) {
        this.date = string;
        this.time = string1;
        this.jumpingjack = string2;
        this.situps = string3;
        this.pushups = string4;
        this.squats = string5;

    }
    public Workout( ) {
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Workout.date = date;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Workout.time = time;
    }

    public String getJumpingjack() {
        return jumpingjack;
    }

    public void setJumpingjack(String jumpingjack) {
        this.jumpingjack = jumpingjack;
    }

    public String getPushups() {
        return pushups;
    }

    public void setPushups(String pushups) {
        this.pushups = pushups;
    }

    public String getSitups() {
        return situps;
    }

    public void setSitups(String situps) {
        this.situps = situps;
    }

    public String getSquats() {
        return squats;
    }

    public void setSquats(String squats) {
        this.squats = squats;
    }
}
