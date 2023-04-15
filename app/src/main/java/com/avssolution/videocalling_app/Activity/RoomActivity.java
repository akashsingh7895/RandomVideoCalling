package com.avssolution.videocalling_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avssolution.videocalling_app.FakeCall.FakeCallActivity;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivityRoomBinding;

import java.util.Random;

public class RoomActivity extends AppCompatActivity {

    ActivityRoomBinding binding;
    Random rand = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int rand_int1 = rand.nextInt(200);
        int rand_int2 = rand.nextInt(150);
        int rand_int3 = rand.nextInt(600);

        binding.tv1.setText(String.valueOf(rand_int1 + " Online"));
        binding.tv2.setText(String.valueOf(rand_int2 + " Online"));
        binding.tv3.setText(String.valueOf(rand_int3 + " Online"));

        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));
            }
        });

        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));
            }
        });

        binding.room3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));
            }
        });


        binding.room4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomActivity.this, FakeCallActivity.class));
            }
        });



    }
}