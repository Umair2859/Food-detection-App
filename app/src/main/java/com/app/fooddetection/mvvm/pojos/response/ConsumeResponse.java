package com.app.fooddetection.mvvm.pojos.response;

import com.app.fooddetection.mvvm.pojos.Super;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumeResponse extends Super {
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("food_item")
    @Expose
    private String foodItem;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("calories")
    @Expose
    private Double calories;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
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
