package com.akashsingh.avs.randomvideocalling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.akashsingh.avs.randomvideocalling.databinding.ActivityCallBinding;
import com.akashsingh.avs.randomvideocalling.databinding.ActivityVideoTestBinding;


public class VideoTest extends AppCompatActivity {

    ActivityVideoTestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}