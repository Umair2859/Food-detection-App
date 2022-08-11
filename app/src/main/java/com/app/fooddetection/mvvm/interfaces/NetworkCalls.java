package com.app.fooddetection.mvvm.interfaces;


import com.app.fooddetection.mvvm.pojos.request.ConsumeRequest;
import com.app.fooddetection.mvvm.pojos.request.PostLoginPojo;
import com.app.fooddetection.mvvm.pojos.request.PostRegPojo;
import com.app.fooddetection.mvvm.pojos.request.SocialLoginPojo;
import com.app.fooddetection.mvvm.pojos.response.CaptureResponse;
import com.app.fooddetection.mvvm.pojos.response.ConsumeResponse;
import com.app.fooddetection.mvvm.pojos.response.RegResponse;
import com.app.fooddetection.mvvm.pojos.response.UserPojo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface NetworkCalls {

    @POST(Urls.URL_GOOGLE_LOGIN)
    Call<RegResponse> postGoogleLogin(@Body SocialLoginPojo postRegPojo);

    @POST(Urls.URL_LOGIN)
    Call<RegResponse> postLogin(@Body PostLoginPojo postRegPojo);

    @POST(Urls.URL_REG)
    Call<RegResponse> postAuth(@Body PostRegPojo postRegPojo);

    @GET(Urls.URL_GET_PROFILE)
    Call<UserPojo> getProfile(@Header("Authorization") String token);

    @POST(Urls.URL_POST_IMAGE_PROCESS)
    @Multipart
    Call<CaptureResponse> postProcessImage(
            @Header("Authorization") String token,
            @Part MultipartBody.Part profileImage);

    @GET(Urls.URL_POST_IMAGE_PROCESS)
    Call<List<CaptureResponse>> getCaptures(
            @Header("Authorization") String token);

    @POST(Urls.URL_POST_CONSUME)
    Call<ConsumeResponse> postConsume(
            @Header("Authorization") String token, @Body ConsumeRequest consumeRequest);

    @GET(Urls.URL_POST_CONSUME)
    Call<List<ConsumeResponse>> getConsumeHistory(
            @Header("Authorization") String token);


}
