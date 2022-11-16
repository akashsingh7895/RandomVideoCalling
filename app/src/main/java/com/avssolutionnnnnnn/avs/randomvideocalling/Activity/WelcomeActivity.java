package com.avssolutionnnnnnn.avs.randomvideocalling.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.avssolutionnnnnnn.avs.randomvideocalling.BuildConfig;
import com.avssolutionnnnnnn.avs.randomvideocalling.MainActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.R;
import com.avssolutionnnnnnn.avs.randomvideocalling.SelectedActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityWelcomeBinding;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.BottomseatBinding;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity implements MaxAdListener {

    ActivityWelcomeBinding  binding ;
    InterstitialAd mInterstitialAd;
    private ActionBarDrawerToggle toggle;


    public static Dialog loadingDialog;

    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //applovin
        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );

        interstitialAd = new MaxInterstitialAd(getString(R.string.Applovin_Inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();




        ///loading Dialog
        loadingDialog = new Dialog(WelcomeActivity.this);
        loadingDialog.setContentView(R.layout.bottomseat);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(650, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();
        /////end loading dialog

        TextView textView = loadingDialog.findViewById(R.id.tvPPDesc);
        TextView aggri = loadingDialog.findViewById(R.id.tvAgree);
        TextView disaggri = loadingDialog.findViewById(R.id.tvDisagree);

        aggri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.dismiss();
            }
        });

        disaggri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });

        textView.setText("By downloading or using the app, these terms will automatically apply to you – you should make sure therefore that you read them carefully before using the app. You’re not allowed to copy, or modify the app, any part of the app, or our trademarks in any way. You’re not allowed to attempt to extract the source code of the app, and you also shouldn’t try to translate the app into other languages, or make derivative versions. The app itself, and all the trade marks, copyright, database rights and other intellectual property rights related to it, still belong to Avs solution .\n" +
                "\n" +
                "Avs solution  is committed to ensuring that the app is as useful and efficient as possible. For that reason, we reserve the right to make changes to the app or to charge for its services, at any time and for any reason. We will never charge you for the app or its services without making it very clear to you exactly what you’re paying for.\n" +
                "\n" +
                "The Live talk - Girls video call  app stores and processes personal data that you have provided to us, in order to provide my Service. It’s your responsibility to keep your phone and access to the app secure. We therefore recommend that you do not jailbreak or root your phone, which is the process of removing software restrictions and limitations imposed by the official operating system of your device. It could make your phone vulnerable to malware/viruses/malicious programs, compromise your phone’s security features and it could mean that the miz app won’t work properly or at all.\n" +
                "\n" +
                "The app does use third party services that declare their own Terms and Conditions.\n" +
                "\n" +
                "Terms and Conditions of third party service providers used by the app are bound to owners.\n" +
                "\n" +
                "Firebase.  Admob.  Google Play Services.  Google Analytics.  Google Crashlytics.         Unity.     \n" +
                "\n" +
                "You should be aware that there are certain things that Avs solution  will not take responsibility for. Certain functions of the app will require the app to have an active internet connection. The connection can be Wi-Fi, or provided by your mobile network provider, but Avs solution  cannot take responsibility for the app not working at full functionality if you don’t have access to Wi-Fi, and you don’t have any of your data allowance left.\n" +
                "\n" +
                "If you’re using the app outside of an area with Wi-Fi, you should remember that your terms of the agreement with your mobile network provider will still apply. As a result, you may be charged by your mobile provider for the cost of data for the duration of the connection while accessing the app, or other third party charges. In using the app, you’re accepting responsibility for any such charges, including roaming data charges if you use the app outside of your home territory (i.e. region or country) without turning off data roaming. If you are not the bill payer for the device on which you’re using the app, please be aware that we assume that you have received permission from the bill payer for using the app.\n" +
                "\n" +
                "Along the same lines, Avs solution  cannot always take responsibility for the way you use the app i.e. You need to make sure that your device stays charged – if it runs out of battery and you can’t turn it on to avail the Service, Avs solution  cannot accept responsibility.\n" +
                "\n" +
                "With respect to Avs solution ’s responsibility for your use of the app, when you’re using the app, it’s important to bear in mind that although we endeavour to ensure that it is updated and correct at all times, we do rely on third parties to provide information to us so that we can make it available to you. Avs solution  accepts no liability for any loss, direct or indirect, you experience as a result of relying wholly on this functionality of the app.\n" +
                "\n" +
                "At some point, we may wish to update the app. The app is currently available on Android – the requirements for system(and for any additional systems we decide to extend the availability of the app to) may change, and you’ll need to download the updates if you want to keep using the app. Avs solution  does not promise that it will always update the app so that it is relevant to you and/or works with the Android version that you have installed on your device. However, you promise to always accept updates to the application when offered to you, We may also wish to stop providing the app, and may terminate use of it at any time without giving notice of termination to you. Unless we tell you otherwise, upon any termination, (a) the rights and licenses granted to you in these terms will end; (b) you must stop using the app, and (if needed) delete it from your device.\n" +
                "\n" +
                "Changes to This Terms and Conditions:\n" +
                "\n" +
                "I may update our Terms and Conditions from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Terms and Conditions on this page.\n" +
                "\n" +
                "These terms and conditions are effective as of 15-11-2022.\n" +
                "\n" +
                "Contact Us:\n" +
                "\n" +
                "If you have any questions or suggestions about my Terms and Conditions, do not hesitate to contact me at singhakash4099@gmail.com.");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

      //  showInterAds();
        Drawer();



        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(WelcomeActivity.this, SelectedActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(WelcomeActivity.this, SelectedActivity.class);
                    startActivity(intent);
                }


            }
        });

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

//    void loadnetiveAd(){
//
//        FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );
//
//        nativeAdLoader = new MaxNativeAdLoader( getString(R.string.Netive), this );
//        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
//        {
//            @Override
//            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
//            {
//                nativeAdContainer.setVisibility(View.VISIBLE);
//                // loadingDialog.dismiss();
//                // Clean up any pre-existing native ad to prevent memory leaks.
//                if ( nativeAd != null )
//                {
//                    nativeAdLoader.destroy( nativeAd );
//                }
//
//                // Save ad for cleanup.
//                nativeAd = ad;
//
//                // Add ad view to view.
//                nativeAdContainer.removeAllViews();
//                nativeAdContainer.addView( nativeAdView );
//            }
//
//            @Override
//            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
//            {
//                nativeAdContainer.setVisibility(View.GONE);
//                // Toast.makeText(MainActivity.this, "NetiveFailed", Toast.LENGTH_SHORT).show();
//                // loadingDialog.dismiss();
//                // We recommend retrying with exponentially higher delays up to a maximum delay
//            }
//
//            @Override
//            public void onNativeAdClicked(final MaxAd ad)
//            {
//                // Optional click callback
//                // loadingDialog.dismiss();
//            }
//        } );
//
//        nativeAdLoader.loadAd();
//
//    }

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
    public void Drawer(){
        toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.drawerOpen,R.string.drawerClose);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        binding. drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setItemIconTintList(null);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            MenuItem menuItem;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuItem = item;


                binding.drawerLayout.closeDrawer(GravityCompat.START);
                binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);

                        switch (menuItem.getItemId()) {

                            case R.id.shareThis:

                                try {
                                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                    shareIntent.setType("text/plain");
                                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                                    String shareMessage= "\nLet me recommend you this application\n\n";
                                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id = com.example.akash.newapp" + BuildConfig.APPLICATION_ID +"\n\n";
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                                } catch(Exception e) {
                                    //e.toString();
                                }

                                break;
                            case R.id.rateThisApp:

                                try{
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                                }
                                catch (ActivityNotFoundException e){
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                                }

                                break;
                            case R.id.contactUs:

                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("message/rfc822");
                                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getString(R.string.supported_email)});
                                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                                try {
                                    startActivity(Intent.createChooser(i, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(WelcomeActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case R.id.privacyPoliy:

                                Intent browserIntent = new Intent(WelcomeActivity.this,PrivicyPolicyActivity.class);
                                startActivity(browserIntent);

                                break;
                            case R.id.logout:
                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                firebaseAuth.signOut();
                                startActivity(new Intent(WelcomeActivity.this, LogInActivity.class));
                        }
                        binding.drawerLayout.removeDrawerListener(this);


                    }
                });

                return true;
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