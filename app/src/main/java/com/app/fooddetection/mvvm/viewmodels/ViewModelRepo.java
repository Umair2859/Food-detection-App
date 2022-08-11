package com.app.fooddetection.mvvm.viewmodels;


import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.fooddetection.mvvm.APIClient;
import com.app.fooddetection.mvvm.interfaces.NetworkCalls;
import com.app.fooddetection.mvvm.mapping_utils.GenericCall;
import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.request.ConsumeRequest;
import com.app.fooddetection.mvvm.pojos.request.PostLoginPojo;
import com.app.fooddetection.mvvm.pojos.request.PostRegPojo;
import com.app.fooddetection.mvvm.pojos.request.SocialLoginPojo;
import com.app.fooddetection.mvvm.pojos.response.CaptureResponse;
import com.app.fooddetection.mvvm.pojos.response.ConsumeResponse;
import com.app.fooddetection.mvvm.pojos.response.RegResponse;
import com.app.fooddetection.mvvm.pojos.response.UserPojo;
import com.app.fooddetection.utils.SharedPrefUtils;
import com.app.fooddetection.utils.Utils;

import java.io.File;
import java.util.List;

public class ViewModelRepo extends AndroidViewModel {

    NetworkCalls networkCalls;

    public ViewModelRepo(@NonNull Application application) {
        super(application);
        networkCalls = APIClient.getRetrofit().create(NetworkCalls.class);
    }

    public LiveData<GenericResponse<RegResponse>> postGoogleCode(SocialLoginPojo socialLoginPojo) {
        return new GenericCall<>(networkCalls.postGoogleLogin(socialLoginPojo)).getMutableLiveData();
    }

    public LiveData<GenericResponse<RegResponse>> postLogin(PostLoginPojo postLoginPojo) {
        return new GenericCall<>(networkCalls.postLogin(postLoginPojo)).getMutableLiveData();
    }

    public LiveData<GenericResponse<RegResponse>> postAuth(PostRegPojo postRegPojo) {
        return new GenericCall<>(networkCalls.postAuth(postRegPojo))
                .getMutableLiveData();
    }

    public LiveData<GenericResponse<UserPojo>> getProfile(Activity context) {
        return new GenericCall<>(networkCalls.getProfile(SharedPrefUtils.getToken(context)))
                .getMutableLiveData();
    }

    public LiveData<GenericResponse<List<CaptureResponse>>> getCaptures(Activity context) {
        return new GenericCall<>(networkCalls.getCaptures(SharedPrefUtils.getToken(context)))
                .getMutableLiveData();
    }

    public LiveData<GenericResponse<CaptureResponse>> postProcessImage(Activity context, File file) {
        return new GenericCall<>(networkCalls.postProcessImage(SharedPrefUtils.getToken(context),
                Utils.fileRequest(file, "image")))
                .getMutableLiveData();
    }

    public LiveData<GenericResponse<ConsumeResponse>> postConsume(Activity context, ConsumeRequest consumeRequest) {
        return new GenericCall<>(networkCalls.postConsume(SharedPrefUtils.getToken(context),
                consumeRequest))
                .getMutableLiveData();
    }

    public LiveData<GenericResponse<List<ConsumeResponse>>> getConsumes(Activity context) {
        return new GenericCall<>(networkCalls.getConsumeHistory(SharedPrefUtils.getToken(context)))
                .getMutableLiveData();
    }

}