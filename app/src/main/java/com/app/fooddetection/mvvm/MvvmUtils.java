package com.app.fooddetection.mvvm;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.response.RegResponse;
import com.app.fooddetection.mvvm.viewmodels.ViewModelRepo;

import java.util.List;

public class MvvmUtils {
    public static ViewModelRepo getViewModelRepo(Activity context) {
        return new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModelRepo.class);
    }

    public static void printErrors(Context context, GenericResponse<RegResponse> regResponseGenericResponse) {
        StringBuilder errors = new StringBuilder();
        try {
            for (String err : regResponseGenericResponse.getErrorMessages())
                errors.append(err).append("\n");
            Toast.makeText(context, errors, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void printGeneralErrors(Context context, List<String> strings) {
        StringBuilder errors = new StringBuilder();
        try {
            for (String err : strings)
                errors.append(err).append("\n");
            Toast.makeText(context, errors, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
