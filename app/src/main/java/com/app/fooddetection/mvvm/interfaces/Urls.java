package com.app.fooddetection.mvvm.interfaces;

public interface Urls {
    String BASE_URL = "http://192.168.43.129:8000";
    String URL_LOGIN = "/auth/login/";
    String URL_REG = "/auth/registration/";
    String URL_GOOGLE_LOGIN = "/auth/google/";
    String URL_POST_IMAGE_PROCESS = "/api/capture/";
    String URL_GET_PROFILE = "/api/my/profile/";
    String URL_POST_CONSUME = "/api/calories-consumption/";
}