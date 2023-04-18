package com.avssolution.videocalling_app;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.avssolution.videocalling_app.Activity.ChatLishActivity;
import com.avssolution.videocalling_app.Activity.GenderActivity;
import com.avssolution.videocalling_app.Activity.WelcomeActivity;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivitySelectedBinding;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;


public class SelectedActivity extends AppCompatActivity implements MaxAdListener {
    ActivitySelectedBinding binding;
    InterstitialAd mInterstitialAd;
    private MaxInterstitialAd applovinInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        binding = ActivitySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadNetiveads();
        loadInterAds();
        applovinInterstitialAd = new MaxInterstitialAd(getString(R.string.applovin_inter),this);
        applovinInterstitialAd.setListener(this);
        applovinInterstitialAd.loadAd();


        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mInterstitialAd != null) {
                    mInterstitialAd.show(SelectedActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            startActivity(new Intent(SelectedActivity.this, GenderActivity.class));

                        }
                    });
                } else {
                    if (applovinInterstitialAd.isReady()){
                        applovinInterstitialAd.showAd();
                        startActivity(new Intent(SelectedActivity.this, GenderActivity.class));

                    }else {
                        startActivity(new Intent(SelectedActivity.this, GenderActivity.class));

                    }

                }

            }
        });


        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mInterstitialAd != null) {
                    mInterstitialAd.show(SelectedActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            startActivity(new Intent(SelectedActivity.this, ChatLishActivity.class));

                        }
                    });
                } else {
                    if (applovinInterstitialAd.isReady()){
                        applovinInterstitialAd.showAd();
                        startActivity(new Intent(SelectedActivity.this, ChatLishActivity.class));

                    }else {
                        startActivity(new Intent(SelectedActivity.this, ChatLishActivity.class));

                    }

                }



            }
        });
    }

    public void loadInterAds(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.inter_ads), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }
    public void loadNetiveads(){
        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, getString(R.string.netive_ads))
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();
                        // TemplateView template = findViewById(R.id.my_template);
                        binding.template.setStyles(styles);
                        binding.template.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
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