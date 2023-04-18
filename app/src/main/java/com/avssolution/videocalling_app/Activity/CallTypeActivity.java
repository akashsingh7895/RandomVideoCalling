package com.avssolution.videocalling_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.avssolution.videocalling_app.FakeCall.FakeCallActivity;
import com.avssolution.videocalling_app.MainActivity;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivityCallTypeBinding;
import com.kaopiz.kprogresshud.KProgressHUD;

public class CallTypeActivity extends AppCompatActivity implements MaxAdListener {

    ActivityCallTypeBinding binding;
    //applovin ads
    private MaxInterstitialAd applovinInterstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    KProgressHUD progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progress = KProgressHUD.create(this);
        progress.setDimAmount(0.5f);
        progress.show();


        applovinInterstitialAd = new MaxInterstitialAd(getString(R.string.applovin_inter),this);
        applovinInterstitialAd.setListener(this);
        applovinInterstitialAd.loadAd();
        loadnetiveAd();

        binding.linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(CallTypeActivity.this, MainActivity.class));

                }else {
                    startActivity(new Intent(CallTypeActivity.this, MainActivity.class));

                }
            }
        });

        binding.linearLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(CallTypeActivity.this, MainActivity.class));

                }else {
                    startActivity(new Intent(CallTypeActivity.this, MainActivity.class));

                }
            }
        });

        binding.fakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(CallTypeActivity.this, FakeCallActivity.class));

                }else {
                    startActivity(new Intent(CallTypeActivity.this, FakeCallActivity.class));

                }
            }
        });
    }


    void loadnetiveAd(){

        FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );

        nativeAdLoader = new MaxNativeAdLoader( getString(R.string.applovin_netive), this );
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                nativeAdContainer.setVisibility(View.VISIBLE);
                progress.dismiss();
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                nativeAdContainer.setVisibility(View.GONE);
                // Toast.makeText(MainActivity.this, "NetiveFailed", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
                progress.dismiss();
            }
        } );

        nativeAdLoader.loadAd();

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