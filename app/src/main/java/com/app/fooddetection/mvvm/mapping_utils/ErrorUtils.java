package com.app.fooddetection.mvvm.mapping_utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ErrorUtils {

    public static List<String> parseError(String response) {
        List<String> errorList = new ArrayList<>();
        try {
            JSONObject jObjError = new JSONObject(response);
            initError("email", errorList, jObjError);
            initError("password1", errorList, jObjError);
            initError("password", errorList, jObjError);
            initError("non_field_errors", errorList, jObjError);
            initError("no_field_errors", errorList, jObjError);
            initError("username", errorList, jObjError);
            initError("detail", errorList, jObjError);
        } catch (JSONException e) {
            e.printStackTrace();
            errorList.add(response);
        }
        return errorList;
    }

    private static void initError(String username, List<String> errorList, JSONObject jObjError) {
        try {
            String err = (String) jObjError.getJSONArray(username).get(0);
            errorList.add(err);

        } catch (Exception e) {
            try {
                String err = jObjError.getString(username);
                errorList.add(err);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }

}
