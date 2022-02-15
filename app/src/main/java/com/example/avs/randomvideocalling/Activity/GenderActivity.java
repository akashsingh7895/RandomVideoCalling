package com.example.avs.randomvideocalling.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.avs.randomvideocalling.MainActivity;
import com.example.avs.randomvideocalling.R;
import com.example.avs.randomvideocalling.databinding.ActivityGenderBinding;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
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

        binding.linearLayout.setEnabled(false);
        binding.linearLayout.setBackground(getResources().getDrawable(R.drawable.white_rounded_bg_dark));
        binding.linearLayout.setEnabled(binding.maleButton.isChecked()|| binding.femaleButton.isChecked());
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.maleButton.isChecked()|| binding.femaleButton.isChecked()){
                    binding.linearLayout.setEnabled(true);
                    binding.linearLayout.setBackground(getResources().getDrawable(R.drawable.white_rounded_bg));

                    binding.linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mInterstitialAd!=null){
                                mInterstitialAd.show(GenderActivity.this);
                                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        super.onAdDismissedFullScreenContent();
                                        Intent intent = new Intent(GenderActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                Intent intent = new Intent(GenderActivity.this,MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    });

                }else {
                    binding.linearLayout.setBackground(getResources().getDrawable(R.drawable.white_rounded_bg_dark));
                    binding.linearLayout.setEnabled(false);
                }
            }
        });
            showInterAds();

        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, getString(R.string.netive_ads))
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();
                        TemplateView template = findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());



    }
    public void showInterAds(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.inter_ads), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });
    }
}