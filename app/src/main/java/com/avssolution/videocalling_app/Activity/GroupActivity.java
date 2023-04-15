package com.avssolution.videocalling_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.SelectedActivity;
import com.avssolution.videocalling_app.databinding.ActivityGroupBinding;


public class GroupActivity extends AppCompatActivity {

    ActivityGroupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroupActivity.this, SelectedActivity.class));


            }
        });

    }

}