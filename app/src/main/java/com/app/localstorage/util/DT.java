package com.app.localstorage.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DT {
    public static  String date(){
        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        return df.format(date);
    }
    public static String time(){
        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        return df.format(date);
    }
}
