package com.avssolution.videocalling_app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.avssolution.videocalling_app.Adapters.ChatListAdapter;
import com.avssolution.videocalling_app.databinding.ActivityChatLishBinding;

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