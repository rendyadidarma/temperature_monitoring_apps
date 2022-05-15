package com.example.temperaturemontoringapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static Temperature temperature = new Temperature();

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        changeFragment(new TemperatureFragment());

        sharedPreferences = getSharedPreferences("com.example.TemperatureApp.RANGETEMP", MODE_PRIVATE);
        double lowestTemp, highestTemp;
        lowestTemp = sharedPreferences.getFloat("low", -1);
        highestTemp = sharedPreferences.getFloat("high", -1);

        temperature.setHIGHEST_TEMP(this, highestTemp);
        temperature.setLOWEST_TEMP(this, lowestTemp);

//        Log.wtf("curr", String.valueOf(temperature.getCURRENT_TEMP()));
        Log.wtf("high", String.valueOf(temperature.getHIGHEST_TEMP()));
        Log.wtf("low", String.valueOf(temperature.getLOWEST_TEMP()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alert_item:
                changeFragment(new AlertFragment());
                break;

            case R.id.temperature_item:
                changeFragment(new TemperatureFragment());
                break;
        }

        return true;
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_main, fragment);
        ft.commit();
    }

}