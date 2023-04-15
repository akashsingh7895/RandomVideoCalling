package com.avssolution.videocalling_app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.avssolution.videocalling_app.databinding.ActivityVideoTestBinding;


public class VideoTest extends AppCompatActivity {

    ActivityVideoTestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}