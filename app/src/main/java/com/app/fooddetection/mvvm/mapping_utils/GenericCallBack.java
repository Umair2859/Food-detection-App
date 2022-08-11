package com.app.fooddetection.mvvm.mapping_utils;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenericCallBack<T> {

    public MutableLiveData<GenericResponse<T>> mutableLiveData;
    Callback<T> callback;

    public GenericCallBack(MutableLiveData<GenericResponse<T>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
        callback = new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                List<String> errors = null;
                try {
                    if (response.errorBody() != null) {
                        String str = response.errorBody().string();
                        errors = ErrorUtils.parseError(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    GenericResponse<T> genericResponse = new GenericResponse<>(response);
                    if (errors != null)
                        genericResponse.setErrorMessages(errors);
                    mutableLiveData.setValue(genericResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    mutableLiveData.setValue(new GenericResponse<>());
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                mutableLiveData.setValue(new GenericResponse<>());
            }
        };
    }

    public Callback<T> getCallback() {
        return callback;
    }

}
