package com.avssolutionnnnnnn.avs.randomvideocalling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityCallBinding;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityVideoTestBinding;
import com.avssolutionnnnnnn.avs.randomvideocalling.network.NetworkAws;

public class VideoTest extends AppCompatActivity {

    ActivityVideoTestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initVideo();
    }
    private void initVideo() {
        new NetworkAws().retriveObj("videoCallingApp","video_0.mp4",binding.videoView);
    }
}