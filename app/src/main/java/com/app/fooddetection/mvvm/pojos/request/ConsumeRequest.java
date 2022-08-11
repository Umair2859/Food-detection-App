package com.app.fooddetection.mvvm.pojos.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumeRequest {
    @SerializedName("food_item")
    @Expose
    private String foodItem;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;

    public ConsumeRequest(String foodItem, Integer quantity, Integer calories) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.calories = calories;
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

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
