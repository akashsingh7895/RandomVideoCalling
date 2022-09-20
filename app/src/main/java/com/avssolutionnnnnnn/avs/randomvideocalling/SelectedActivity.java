package com.avssolutionnnnnnn.avs.randomvideocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avssolutionnnnnnn.avs.randomvideocalling.Activity.ChatLishActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.Activity.GenderActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.Activity.GroupActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.Adapters.ChatListAdapter;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivitySelectedBinding;

public class SelectedActivity extends AppCompatActivity {
    ActivitySelectedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        binding = ActivitySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectedActivity.this, GenderActivity.class));
            }
        });


        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectedActivity.this, ChatLishActivity.class));


            }
        });
    }
}