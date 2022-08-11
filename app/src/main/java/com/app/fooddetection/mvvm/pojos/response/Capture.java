package com.app.fooddetection.mvvm.pojos.response;

import com.app.fooddetection.mvvm.pojos.Super;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capture extends Super {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("x_axis")
    @Expose
    private String xAxis;
    @SerializedName("y_axis")
    @Expose
    private String yAxis;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
