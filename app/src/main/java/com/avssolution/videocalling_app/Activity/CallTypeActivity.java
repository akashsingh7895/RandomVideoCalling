package com.avssolution.videocalling_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avssolution.videocalling_app.FakeCall.FakeCallActivity;
import com.avssolution.videocalling_app.MainActivity;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivityCallTypeBinding;

public class CallTypeActivity extends AppCompatActivity {

    ActivityCallTypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CallTypeActivity.this, MainActivity.class));
            }
        });

        binding.linearLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CallTypeActivity.this, MainActivity.class));
            }
        });

        binding.fakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CallTypeActivity.this, FakeCallActivity.class));
            }
        });
    }
}