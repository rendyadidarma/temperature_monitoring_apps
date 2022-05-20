package com.example.temperaturemontoringapps;

import static com.example.temperaturemontoringapps.MainActivity.temperature;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AlertFragment extends Fragment implements View.OnClickListener {
    TextView showTempMax, showTempMin;
    Button btnEditTemp, btnSaveTemp;
    EditText etTempMax, etTempMin;

    LinearLayout linearEditMax, linearEditMin, linearShowMax, linearShowMin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etTempMax = view.findViewById(R.id.addTempMax);
        etTempMin = view.findViewById(R.id.addTempMin);
        linearEditMax = view.findViewById(R.id.editTempMax);
        linearEditMin = view.findViewById(R.id.editTempMin);
        showTempMax = view.findViewById(R.id.tempMax);
        showTempMin = view.findViewById(R.id.tempMin);
        linearShowMax = view.findViewById(R.id.showTempMax);
        linearShowMin = view.findViewById(R.id.showTempMin);
        btnEditTemp = view.findViewById(R.id.btnEditTemp);
        btnSaveTemp = view.findViewById(R.id.btnSaveTemp);

        init();

        btnEditTemp.setOnClickListener(this);
        btnSaveTemp.setOnClickListener(this);
    }

    private void init() {
        if(temperature != null) {
            showTempMax.setText(String.format("%s", temperature.getHIGHEST_TEMP()));
            showTempMin.setText(String.format("%s", temperature.getLOWEST_TEMP()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditTemp:
                showTempMin.setVisibility(View.GONE);
                showTempMax.setVisibility(View.GONE);
                btnEditTemp.setVisibility(View.GONE);
                linearShowMax.setVisibility(View.GONE);
                linearShowMin.setVisibility(View.GONE);

                etTempMax.getText().clear();
                etTempMin.getText().clear();
                etTempMax.setVisibility(View.VISIBLE);
                etTempMin.setVisibility(View.VISIBLE);
                linearEditMax.setVisibility(View.VISIBLE);
                linearEditMin.setVisibility(View.VISIBLE);
                btnSaveTemp.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSaveTemp:
                etTempMax.setVisibility(View.GONE);
                etTempMin.setVisibility(View.GONE);
                btnSaveTemp.setVisibility(View.GONE);
                showTempMin.setVisibility(View.VISIBLE);
                showTempMax.setVisibility(View.VISIBLE);
                linearShowMax.setVisibility(View.VISIBLE);
                linearShowMin.setVisibility(View.VISIBLE);
                btnEditTemp.setVisibility(View.VISIBLE);
                linearEditMin.setVisibility(View.GONE);
                linearEditMax.setVisibility(View.GONE);

                if(!etTempMax.getText().toString().equalsIgnoreCase("") && !etTempMin.getText().toString().equalsIgnoreCase("")) {
                    temperature.setHIGHEST_TEMP(getActivity(), Double.parseDouble(etTempMax.getText().toString()));
                    temperature.setLOWEST_TEMP(getActivity(), Double.parseDouble(etTempMin.getText().toString()));
                    init();
                    Toast.makeText(getActivity(), "Input succeed!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "High and Low Temperature Cannot be Empty", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}