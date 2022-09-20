package com.avssolutionnnnnnn.avs.randomvideocalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.avssolutionnnnnnn.avs.randomvideocalling.Activity.ConnectingActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.Activity.LogInActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.Activity.RewardActivity;
import com.avssolutionnnnnnn.avs.randomvideocalling.Models.User;
import com.avssolutionnnnnnn.avs.randomvideocalling.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    long coins = 0;
    String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    private int requestCode = 1;
    User user;
    KProgressHUD progress;

    InterstitialAd mInterstitialAd;

    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        progress = KProgressHUD.create(this);
        progress.setDimAmount(0.5f);
        progress.show();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();

        firebaseNotification();

        database.getReference().child("profiles")
                .child(currentUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        progress.dismiss();
                        user = snapshot.getValue(User.class);
                        coins = user.getCoins();

                        binding.coins.setText("You have: " + coins);

                        Glide.with(MainActivity.this)
                                .load(user.getProfile())
                                .into(binding.profilePicture);
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


        binding.findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionsGranted()) {
                    if (coins > 10) {
                        coins = coins - 10;
                        database.getReference().child("profiles")
                                .child(currentUser.getUid())
                                .child("coins")
                                .setValue(coins);
                        Intent intent = new Intent(MainActivity.this, ConnectingActivity.class);
                        intent.putExtra("profile", user.getProfile());
                        startActivity(intent);
                        //startActivity(new Intent(MainActivity.this, ConnectingActivity.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Insufficient Coins", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    askPermissions();
                }

            }
        });

        binding.rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RewardActivity.class));
            }
        });

       // showInterAds();
        Drawer();


    }

    void askPermissions(){
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    private boolean isPermissionsGranted() {
        for(String permission : permissions ){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }

        return true;

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

  void firebaseNotification(){
      FirebaseMessaging.getInstance().subscribeToTopic("VideoChat")
              .addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      String msg ="Done";
                      if (!task.isSuccessful()) {
                          msg = "Failed";
                      }

                      Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                                  Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                              }

                              break;
                          case R.id.privacyPoliy:

                              Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy)));
                              startActivity(browserIntent);

                              break;
                          case R.id.termsCondi:
                              Intent searchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_condition)));
                              startActivity(searchIntent);

                              break;
                          case R.id.logout:
                              FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                              firebaseAuth.signOut();
                              startActivity(new Intent(MainActivity.this, LogInActivity.class));
                      }
                      binding.drawerLayout.removeDrawerListener(this);


                  }
              });

              return true;
          }
      });



  }
}