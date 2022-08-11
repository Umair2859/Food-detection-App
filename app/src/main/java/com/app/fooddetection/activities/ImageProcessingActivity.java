package com.app.fooddetection.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fooddetection.R;
import com.app.fooddetection.mvvm.MvvmUtils;
import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.request.ConsumeRequest;
import com.app.fooddetection.mvvm.pojos.response.CaptureResponse;
import com.app.fooddetection.mvvm.pojos.response.ConsumeResponse;
import com.app.fooddetection.utils.DialogUtils;
import com.app.fooddetection.utils.ImageProcessor;
import com.app.fooddetection.utils.Utils;

public class ImageProcessingActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvTitle;
    TextView tvCal;
    TextView tvSuggestion;
    Dialog loadingDialog;
    EditText etFoodItems;

    CaptureResponse captureResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_processing);

        initViews();

        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);

        if (DashboardActivity.bitmap != null) {
            imageView.setImageBitmap(DashboardActivity.bitmap);
            processImage();
        }

    }

    private void initViews() {
        imageView = findViewById(R.id.iv_preview);
        tvTitle = findViewById(R.id.tv_title);
        tvCal = findViewById(R.id.tv_cal);
        tvSuggestion = findViewById(R.id.tv_suggestion);
        etFoodItems = findViewById(R.id.et_food_items);
    }

    private void processImage() {
        loadingDialog.show();
        MvvmUtils.getViewModelRepo(this)
                .postProcessImage(this, ImageProcessor.getFileFromBitmap(this, DashboardActivity.bitmap))
                .observe(this, this::initImageResp);
    }

    private void initImageResp(GenericResponse<CaptureResponse> imageResponseGenericResponse) {
        loadingDialog.dismiss();
        if (imageResponseGenericResponse.isSuccessful()) {
            captureResponse = imageResponseGenericResponse.getResponse();
            String calories = captureResponse.getCalories() + " calories";
            tvCal.setText(calories);
            tvTitle.setText(captureResponse.getCategory());
            tvSuggestion.setText(Utils.getSuggestion(captureResponse.getCalories(), Utils.userPojo.getBmi()));
        } else
            MvvmUtils.printGeneralErrors(this, imageResponseGenericResponse.getErrorMessages());
    }

    public void back(View view) {
        finish();
    }

    public void submit(View view) {
        String strEtFoodItems = etFoodItems.getText().toString();
        if (strEtFoodItems.isEmpty())
            return;
        if (captureResponse == null) {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
            return;
        }
        int intFoodItems = Integer.parseInt(strEtFoodItems);
        String text = "You have gained "
                + String.valueOf(Utils.getBMIGain(captureResponse.getCalories(), intFoodItems)).substring(0, 3)
                + " indexed in your bmi";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        ConsumeRequest consumeRequest = new ConsumeRequest(captureResponse.getCategory(), intFoodItems, ((int) captureResponse.getCalories()) * intFoodItems);
        loadingDialog.show();
        MvvmUtils.getViewModelRepo(this)
                .postConsume(this, consumeRequest)
                .observe(this, this::initConsumeResp);
    }

    private void initConsumeResp(GenericResponse<ConsumeResponse> consumeResponseGenericResponse) {
        loadingDialog.dismiss();
        if (consumeResponseGenericResponse.isSuccessful()) {
            startActivity(new Intent(this, CaloriesConsumedActivity.class));
            finish();
        } else
            MvvmUtils.printGeneralErrors(this, consumeResponseGenericResponse.getErrorMessages());
    }
}