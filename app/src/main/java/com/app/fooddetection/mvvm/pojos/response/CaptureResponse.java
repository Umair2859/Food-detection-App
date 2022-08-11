package com.app.fooddetection.mvvm.pojos.response;

import com.app.fooddetection.mvvm.pojos.Super;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaptureResponse extends Super {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("accuracy")
    @Expose
    private Double accuracy;
    @SerializedName("calories")
    @Expose
    private Double calories;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }
}
