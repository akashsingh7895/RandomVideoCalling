package com.akashsingh.avs.randomvideocalling;

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
import com.akashsingh.avs.randomvideocalling.Activity.ChatLishActivity;
import com.akashsingh.avs.randomvideocalling.Activity.GenderActivity;
import com.akashsingh.avs.randomvideocalling.databinding.ActivitySelectedBinding;

public class SelectedActivity extends AppCompatActivity implements MaxAdListener {
    ActivitySelectedBinding binding;


    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        binding = ActivitySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        interstitialAd = new MaxInterstitialAd(getString(R.string.Applovin_Inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();
        loadnetiveAd();

        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    startActivity(new Intent(SelectedActivity.this, GenderActivity.class));

                }else {
                    startActivity(new Intent(SelectedActivity.this, GenderActivity.class));

                }
            }
        });


        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    startActivity(new Intent(SelectedActivity.this, ChatLishActivity.class));

                }else {
                    startActivity(new Intent(SelectedActivity.this, ChatLishActivity.class));

                }

            }
        });
    }


    void loadnetiveAd(){

        FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );

        nativeAdLoader = new MaxNativeAdLoader( getString(R.string.Applovin_Netive), this );
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                nativeAdContainer.setVisibility(View.VISIBLE);
                // loadingDialog.dismiss();
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
                // loadingDialog.dismiss();
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
                // loadingDialog.dismiss();
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