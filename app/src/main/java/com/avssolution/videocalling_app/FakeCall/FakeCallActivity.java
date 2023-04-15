package com.avssolution.videocalling_app.FakeCall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.avssolution.videocalling_app.Activity.CallActivity;
import com.avssolution.videocalling_app.MainActivity;
import com.avssolution.videocalling_app.R;
import com.avssolution.videocalling_app.databinding.ActivityFakeCallBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Random;

public class FakeCallActivity extends AppCompatActivity {

    ActivityFakeCallBinding binding;
    Camera camera;
    ShowCamera showCamera;

    int camBackId = Camera.CameraInfo.CAMERA_FACING_BACK;
    int camFrontId = Camera.CameraInfo.CAMERA_FACING_FRONT;
    Camera.CameraInfo currentCamInfo = new Camera.CameraInfo();


    int num = 0;
    int num1 = 0;

    FirebaseFirestore firebaseFirestore;
    List<String> list;


    public static Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFakeCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        camera = Camera.open(camFrontId);
        showCamera = new ShowCamera(this,camera);
        binding.frameLayout.addView(showCamera);



        alertDialog = new Dialog(FakeCallActivity.this);
        alertDialog.setContentView(R.layout.report_dialoag);
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        alertDialog.getWindow().setLayout(650, ViewGroup.LayoutParams.WRAP_CONTENT);


        firebaseFirestore = FirebaseFirestore.getInstance();


        firebaseFirestore.collection("video_call")
                .document("videos")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot shot = task.getResult();
                            list = (List<String>) shot.get("url");

                            Random random = new Random();
                            for(int i=0; i < list.size(); i++){
                                String random_list = list.get( random.nextInt(list.size()));
                                Log.d("ran",""+random_list);
                               // Toast.makeText(FakeCallActivity.this, ""+random_list, Toast.LENGTH_SHORT).show();
                                Uri uri = Uri.parse(String.valueOf(random_list));
                                binding.videoView.setVideoURI(uri);
                            }
                            Log.d("list",""+list);


                        }else {
                            Toast.makeText(FakeCallActivity.this, ""+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
                Toast.makeText(FakeCallActivity.this, "Internet Is slow ! Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                binding.frameLayout.setVisibility(View.VISIBLE);
                binding.videoView.start();
                binding.connectingImage.setVisibility(View.GONE);
                binding.loadingAnimation.setVisibility(View.GONE);
            }
        });

        binding.icAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });


        CardView no = alertDialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        CardView yes = alertDialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(FakeCallActivity.this, "User Blocked Successfully !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FakeCallActivity.this, MainActivity.class));
            }
        });

        binding.micBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (num==0){
                    binding.micBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24));
                    num = 1;
                }else if (num==1){
                    binding.micBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_volume_up_24));
                    num = 0;
                }

            }
        });

        binding.videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (num1==0){
                    binding.videoBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_mic_off_24));
                    num1 = 1;
                }else if (num1==1){
                    binding.videoBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_mic_24));
                    num1 = 0;
                }

            }
        });

        binding.endCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}