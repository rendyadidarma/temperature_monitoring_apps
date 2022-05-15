package com.example.temperaturemontoringapps;

import static com.example.temperaturemontoringapps.MainActivity.temperature;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        binding = null;
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://iotemp-6553a-default-rtdb.firebaseio.com/");
        DatabaseReference temperatureData = database.getReference("Result").child("Temperature");

        temperatureData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double value = snapshot.getValue(Double.class);
                if(value != null) {
                    binding.curTemp.setText("" + value);

                    if (value <= temperature.getLOWEST_TEMP()) {
                        binding.lowDetect.setVisibility(View.VISIBLE);
                    } else if (value >= temperature.getHIGHEST_TEMP()) {
                        binding.highDetect.setVisibility(View.VISIBLE);
                    } else {
                        binding.highDetect.setVisibility(View.GONE);
                        binding.lowDetect.setVisibility(View.GONE);
                    }
                }

                Log.wtf("value", "The temperature now is " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w("Error", "Failed to read value.");

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}