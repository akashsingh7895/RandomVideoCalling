package com.avssolutionnnnnnn.avs.randomvideocalling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.avssolutionnnnnnn.avs.randomvideocalling.R;
import com.avssolutionnnnnnn.avs.randomvideocalling.SelectedActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityGroupBinding;

public class GroupActivity extends AppCompatActivity implements MaxAdListener {

    ActivityGroupBinding binding;
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        interstitialAd = new MaxInterstitialAd(getString(R.string.Applovin_Inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                }else {
                    startActivity(new Intent(GroupActivity.this, SelectedActivity.class));

                }

            }
        });

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