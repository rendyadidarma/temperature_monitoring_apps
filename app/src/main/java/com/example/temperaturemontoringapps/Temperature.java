package com.example.temperaturemontoringapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Temperature {

    private double HIGHEST_TEMP, LOWEST_TEMP;

    public Temperature() {
    }


    public double getHIGHEST_TEMP() {
        return HIGHEST_TEMP;
    }

    public void setHIGHEST_TEMP(Context context, double HIGHEST_TEMP) {
        this.HIGHEST_TEMP = HIGHEST_TEMP;
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.TemperatureApp.RANGETEMP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("high", (float) HIGHEST_TEMP);
        editor.apply();
    }

    public double getLOWEST_TEMP() {
        return LOWEST_TEMP;
    }

    public void setLOWEST_TEMP(Context context, double LOWEST_TEMP) {
        this.LOWEST_TEMP = LOWEST_TEMP;
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.TemperatureApp.RANGETEMP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("low", (float) LOWEST_TEMP);
        editor.apply();
    }

}
