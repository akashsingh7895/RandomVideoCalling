package com.akashsingh.avs.randomvideocalling.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.akashsingh.avs.randomvideocalling.Adapters.ListAdapter;
import com.akashsingh.avs.randomvideocalling.R;
import com.akashsingh.avs.randomvideocalling.databinding.ActivityGenderBinding;

import com.google.android.gms.ads.interstitial.InterstitialAd;

public class GenderActivity extends AppCompatActivity implements MaxAdListener {

    ActivityGenderBinding binding;
    InterstitialAd mInterstitialAd;

    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addAdapter();
        interstitialAd = new MaxInterstitialAd(getString(R.string.Applovin_Inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();

        if (interstitialAd.isReady()){
            interstitialAd.showAd();
        }
    }

    private void addAdapter(){
        binding.rv.setAdapter(new ListAdapter(getApplicationContext()));
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GenderActivity.this,WelcomeActivity.class));
    }

    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

    }
}