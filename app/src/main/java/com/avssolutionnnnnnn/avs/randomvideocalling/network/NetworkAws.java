package com.avssolutionnnnnnn.avs.randomvideocalling.network;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.VideoView;


import com.avssolutionnnnnnn.avs.randomvideocalling.Models.aws.ModelRetriveObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkAws {

    public void retriveObj(String objFolderNm, String objectFullNm,VideoView videoView){
        Call<ModelRetriveObj> call =  ((JsonPlaceHolderApi) new Retrofit.Builder()
                .baseUrl("https://ajsofttech19.xyz/").
                addConverterFactory(GsonConverterFactory.create()).
                build().
                create(JsonPlaceHolderApi.class))
                .getData("ajsofttech@AWS", objFolderNm, objectFullNm);

        call.enqueue(new Callback<ModelRetriveObj>() {
            public void onFailure(Call<ModelRetriveObj> call, Throwable th) {
                Log.d("sadsdv","failes"+th.getMessage());
            }
            public void onResponse(Call<ModelRetriveObj> call, Response<ModelRetriveObj> response) {
                ModelRetriveObj body = response.body();
                Log.d("sadsdv","getObjectUrl"+body.getObjectUrl());




                videoView.setVideoURI(Uri.parse(body.objectUrl));
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        //  NetworkAws.this.addVideoIsSeenToDB(configDatabasePOJO2, num2);
                        // progressBar2.setVisibility(View.GONE);
                        mediaPlayer.start();
                    }
                });
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        //  progressBar2.setVisibility(View.GONE);
                    }
                });
                videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        Log.d("TEST", ">>>>>>>>>> involked... setOnErrorListener()");
                        return true;
                    }
                });



            }
        });
    }


}





