package com.example.temperaturemontoringapps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.temperaturemontoringapps.databinding.FragmentFixTempBinding;

public class FixTempFragment extends Fragment {

    private int flag;
    private FragmentFixTempBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            flag = getArguments().getInt("flag");
//            Toast.makeText(getActivity(), "Nilai Flag : " + flag, Toast.LENGTH_SHORT).show();
        }
        binding = FragmentFixTempBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(flag == -1) {
            Toast.makeText(getActivity(), "Temperature Normal.", Toast.LENGTH_SHORT).show();
        } else if(flag == 0) { // temp low
            binding.fixTitle.setText(R.string.low_recommend_title);
            binding.fixContent.setText(R.string.low_recommend_point);
        } else if(flag == 1) {
            binding.fixTitle.setText(R.string.high_recommend_title);
            binding.fixContent.setText(R.string.high_recommend_point);
        }
    }
}