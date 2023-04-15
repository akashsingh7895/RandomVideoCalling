package com.avssolution.videocalling_app.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.avssolution.videocalling_app.Adapters.ListAdapter;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivityGenderBinding;

import com.google.android.gms.ads.interstitial.InterstitialAd;

public class GenderActivity extends AppCompatActivity{

    ActivityGenderBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addAdapter();

    }

    private void addAdapter(){
        binding.rv.setAdapter(new ListAdapter(getApplicationContext()));
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GenderActivity.this, WelcomeActivity.class));
    }

}