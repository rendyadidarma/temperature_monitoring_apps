package com.example.temperaturemontoringapps;

import static com.example.temperaturemontoringapps.MainActivity.temperature;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temperaturemontoringapps.databinding.FragmentTemperatureBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TemperatureFragment extends Fragment {

    private FragmentTemperatureBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTemperatureBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        created();
    }

    public void created() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://iotemp-6553a-default-rtdb.firebaseio.com/");
        DatabaseReference temperatureData = database.getReference("Result");


        temperatureData.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Double valueTemp = null, valueHum = null;

                if (snapshot.hasChildren() && snapshot.exists()) {
                    valueTemp = snapshot.child("Temperature").getValue(Double.class);
                    Log.wtf("value", "The temperature now is " + valueTemp);


                    if (valueTemp <= temperature.getLOWEST_TEMP()) {
                        binding.lowDetect.setVisibility(View.VISIBLE);
                        binding.warnLow.setVisibility(View.VISIBLE);
                        binding.fixTemp.setVisibility(View.VISIBLE);

                    } else if (valueTemp >= temperature.getHIGHEST_TEMP()) {
                        binding.highDetect.setVisibility(View.VISIBLE);
                        binding.warnHigh.setVisibility(View.VISIBLE);
                        binding.fixTemp.setVisibility(View.VISIBLE);

                    } else {
                        binding.highDetect.setVisibility(View.GONE);
                        binding.lowDetect.setVisibility(View.GONE);
                    }

                    valueHum = snapshot.child("Humidity").getValue(Double.class);

                    Log.wtf("value", "The temperature now is " + valueHum);
                } else {
                    Log.wtf("No Data Error", "Error No Data Found!");
                }

                TextView temp = binding.curTemp;
                TextView humidity = binding.curHumid;
                if(valueTemp != null) {
                    temp.setText(valueTemp.toString());
                }

                if(valueHum != null) {
                    humidity.setText(valueHum.toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", "Failed to read value.");
            }
        });
    }
}