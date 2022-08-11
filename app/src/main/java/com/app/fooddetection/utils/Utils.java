package com.app.fooddetection.utils;

import android.widget.EditText;

import com.app.fooddetection.mvvm.pojos.response.UserPojo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class Utils {
    public static UserPojo userPojo;

    public static boolean validEt(EditText etUserName, String strEtUserName) {
        if (strEtUserName.isEmpty()) {
            etUserName.setError("Field Empty");
            return false;
        }
        return true;
    }

    public static MultipartBody.Part fileRequest(File file, String image) {
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                file);
        return MultipartBody.Part.createFormData(image, file.getName(), fileReqBody);
    }

    public static String getSuggestion(double calories, double bmi) {
        double factor;
        String suggestion = "Normal You can consume without any worry";
        if (bmi < 18.5) {
            factor = 18.5 - bmi;
            int sugItems = (int) ((int) (factor * 3 * 3500) / calories);
            suggestion = "Eat " + sugItems + " these food items within 8 days to maintain your BMI";
        } else if (bmi > 25) {
            factor = bmi - 25;
            int sugItems = (int) ((int) (factor * 3 * 3500) / calories);
            suggestion = "Eat healthy - calories equivalent to " + sugItems + " of these food items must be burned to maintain BMI";
        }
        return suggestion;
    }

    public static double getBMIGain(double calories, int quantity) {
        return (calories * quantity) / (10500);
    }


}
