package com.app.fooddetection.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fooddetection.R;
import com.app.fooddetection.info.Info;
import com.app.fooddetection.mvvm.MvvmUtils;
import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.response.UserPojo;
import com.app.fooddetection.utils.DialogUtils;
import com.app.fooddetection.utils.GalleryUtils;
import com.app.fooddetection.utils.SharedPrefUtils;
import com.app.fooddetection.utils.Utils;

public class DashboardActivity extends AppCompatActivity {

    public static Bitmap bitmap;
    Dialog loadingDialog;
    TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvBMI = findViewById(R.id.bmi);
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);

        initUserData();
    }

    private void initUserData() {
        loadingDialog.show();
        MvvmUtils.getViewModelRepo(this)
                .getProfile(DashboardActivity.this)
                .observe(this, this::initResponse);

    }

    private void initResponse(GenericResponse<UserPojo> userPojoGenericResponse) {
        loadingDialog.dismiss();
        if (userPojoGenericResponse.isSuccessful()) {
            Utils.userPojo = userPojoGenericResponse.getResponse();
            String bmi = "BMI : " + Utils.userPojo.getBmi();
            tvBMI.setText(bmi);
        } else {
            MvvmUtils.printGeneralErrors(this, userPojoGenericResponse.getErrorMessages());
            SharedPrefUtils.putStringSharedPrefs(this, "", Info.TOKEN);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void camera(View view) {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 11);
        else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 22);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            startActivity(new Intent(DashboardActivity.this, ImageProcessingActivity.class));
        } else if (requestCode == Info.REQUEST_CODE) {
            bitmap = GalleryUtils.getBitmap(this, resultCode, data);
            startActivity(new Intent(this, ImageProcessingActivity.class));
        }
    }

    public void gallery(View view) {
        openGallery();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, Info.REQUEST_CODE);
    }


    public void consumed(View view) {
        startActivity(new Intent(this, CaloriesConsumedActivity.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, CaptureHistoryActivity.class));
    }
}