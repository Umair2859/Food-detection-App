package com.app.fooddetection.mvvm.pojos.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostLoginPojo {

    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("password")
    @Expose
    String password;

    public PostLoginPojo() {

    }

    public PostLoginPojo(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
