package com.avssolutionnnnnnn.avs.randomvideocalling.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.avssolutionnnnnnn.avs.randomvideocalling.Adapters.ChatListAdapter;
import com.avssolutionnnnnnn.avs.randomvideocalling.R;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityChatLishBinding;

public class ChatLishActivity extends AppCompatActivity {

    ActivityChatLishBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatLishBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addAdapter();
    }
    private void addAdapter(){
        binding.rv1.setAdapter(new ChatListAdapter(getApplicationContext()));
        binding.rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}