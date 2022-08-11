package com.app.fooddetection.mvvm.pojos.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialLoginPojo {

    @SerializedName("access_token")
    @Expose
    String accessToken;

    @SerializedName("id_token")
    @Expose
    String idToken;

    @SerializedName("code")
    @Expose
    String authCode;

    public SocialLoginPojo(String accessToken, String idToken, String authCode) {
        this.accessToken = accessToken;
        this.idToken = idToken;
        this.authCode = authCode;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
