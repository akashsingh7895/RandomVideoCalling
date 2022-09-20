package com.avssolutionnnnnnn.avs.randomvideocalling.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.avssolutionnnnnnn.avs.randomvideocalling.Adapters.ChatListAdapter;
import com.avssolutionnnnnnn.avs.randomvideocalling.Adapters.ListAdapter;
import com.avssolutionnnnnnn.avs.randomvideocalling.MainActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.R;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityGenderBinding;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

public class GenderActivity extends AppCompatActivity {

    ActivityGenderBinding binding;
    InterstitialAd mInterstitialAd;

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


}