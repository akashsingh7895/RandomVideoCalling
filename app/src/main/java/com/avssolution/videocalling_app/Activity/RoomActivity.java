package com.avssolution.videocalling_app.Activity;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.avssolution.videocalling_app.FakeCall.FakeCallActivity;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivityRoomBinding;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Random;

public class RoomActivity extends AppCompatActivity implements MaxAdListener {

    ActivityRoomBinding binding;
    Random rand = new Random();
    private MaxInterstitialAd applovinInterstitialAd;

    KProgressHUD progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progress = KProgressHUD.create(this);
        progress.setDimAmount(0.5f);
        progress.show();


        applovinInterstitialAd = new MaxInterstitialAd( getString(R.string.applovin_inter), this );
        applovinInterstitialAd.setListener(this);
        applovinInterstitialAd.loadAd();

        int rand_int1 = rand.nextInt(200);
        int rand_int2 = rand.nextInt(150);
        int rand_int3 = rand.nextInt(600);
        loadNetiveads();

        binding.tv1.setText(String.valueOf(rand_int1 + " Online"));
        binding.tv2.setText(String.valueOf(rand_int2 + " Online"));
        binding.tv3.setText(String.valueOf(rand_int3 + " Online"));

        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));

                }else {
                    startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));

                }
            }
        });

        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));

                }else {
                    startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));

                }
            }
        });

        binding.room3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));

                }else {
                    startActivity(new Intent(RoomActivity.this,CallTypeActivity.class));

                }
            }
        });


        binding.room4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (applovinInterstitialAd.isReady()){
                    applovinInterstitialAd.showAd();
                    startActivity(new Intent(RoomActivity.this, FakeCallActivity.class));

                }else {
                    startActivity(new Intent(RoomActivity.this, FakeCallActivity.class));

                }
            }
        });



    }

    public void loadNetiveads(){
        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, getResources().getString(R.string.netive_ads))
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();
                        binding.template.setStyles(styles);
                        binding.template.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onAdLoaded(MaxAd ad) {
      progress.dismiss();
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
      progress.dismiss();
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

    }
}