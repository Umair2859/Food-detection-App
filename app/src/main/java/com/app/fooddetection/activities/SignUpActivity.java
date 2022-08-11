package com.app.fooddetection.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fooddetection.R;
import com.app.fooddetection.mvvm.pojos.response.UserPojo;
import com.app.fooddetection.utils.DialogUtils;
import com.app.fooddetection.utils.Utils;

public class SignUpActivity extends AppCompatActivity {

    public static UserPojo userPojo;
    public static String strEtPassword;
    public static Activity context;
    boolean isPassVisible = false;
    EditText etUserName;
    EditText etEmail;
    EditText etAge;
    EditText etPassword;
    EditText etConfirmPassword;
    String strEtUserName;
    String strEtEmail;
    String strEtAge;
    String strEtConfirmPassword;
    Dialog dgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;
        initViews();
        dgLoading = new Dialog(this);
        DialogUtils.initLoadingDialog(dgLoading);
    }


    public void showPassword(View view) {
        if (!isPassVisible) {
            etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPassVisible = true;
        } else {
            etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPassVisible = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void castStrings() {
        strEtAge = etAge.getText().toString();
        strEtEmail = etEmail.getText().toString();
        strEtUserName = etUserName.getText().toString();
        strEtPassword = etPassword.getText().toString();
        strEtConfirmPassword = etConfirmPassword.getText().toString();
    }

    private void initViews() {
        etUserName = findViewById(R.id.et_food_items);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);
        etAge = findViewById(R.id.et_age);
        etConfirmPassword = findViewById(R.id.et_confirm_pass);
    }

    public void back(View view) {
        finish();
    }

    public void signUp(View view) {
        castStrings();
        if (!isEverythingValid()) {
            Toast.makeText(this, "Fields invalid please check", Toast.LENGTH_SHORT).show();
            return;
        }

        userPojo = new UserPojo(strEtUserName, strEtEmail, 0.0, 0.0, 0);
        startActivity(new Intent(this, BMICalculationActivity.class));
    }

    private boolean isEverythingValid() {
        if (!Utils.validEt(etUserName, strEtUserName))
            return false;

        if (!Utils.validEt(etEmail, strEtEmail))
            return false;

        if (!Utils.validEt(etAge, strEtAge))
            return false;

        if (!Utils.validEt(etPassword, strEtPassword))
            return false;

        return strEtPassword.equals(strEtConfirmPassword);
    }

}