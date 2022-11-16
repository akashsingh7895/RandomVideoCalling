package com.avssolutionnnnnnn.avs.randomvideocalling.network;


import com.avssolutionnnnnnn.avs.randomvideocalling.Models.aws.ModelRetriveObj;
import com.google.gson.JsonElement;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/retriveObjectUrl")
    Call<ModelRetriveObj> getData(@Query("apiKey") String apiKey,
                                  @Query("objectFolderName") String objectFolderName,
                                  @Query("objectFullName") String objectFullName);





}
