package com.app.fooddetection.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fooddetection.info.Info;
import com.app.fooddetection.R;
import com.app.fooddetection.mvvm.MvvmUtils;
import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.request.PostLoginPojo;
import com.app.fooddetection.mvvm.pojos.response.RegResponse;
import com.app.fooddetection.utils.DialogUtils;
import com.app.fooddetection.utils.SharedPrefUtils;
import com.app.fooddetection.utils.Utils;

public class MainActivity extends AppCompatActivity {
    public static Activity context;
    EditText etEmail;
    EditText etPassword;
    String strEtEmail;
    String strEtPassword;
    boolean isPassVisible = false;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);

        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);
//
//        if (!SharedPrefUtils.getToken(this).isEmpty()) {
//            startActivity(new Intent(this, DashboardActivity.class));
//            finish();
//        }

    }

    public void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void showPassword(View view) {
        if (!isPassVisible) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPassVisible = true;
        } else {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPassVisible = false;
        }

    }

    private void castStrings() {
        strEtEmail = etEmail.getText().toString();
        strEtPassword = etPassword.getText().toString();
    }

    private boolean isEverythingValid() {
        if (!Utils.validEt(etEmail, strEtEmail))
            return false;
        return Utils.validEt(etPassword, strEtPassword);
    }

    public void Login(View view) {
        castStrings();
        if (!isEverythingValid())
            return;
        initSignIn();
    }

    private void initSignIn() {
        castStrings();
        loadingDialog.show();
        MvvmUtils.getViewModelRepo(this).postLogin(new PostLoginPojo(strEtEmail, strEtPassword)).observe(this, this::initResponse);
    }

    private void initResponse(GenericResponse<RegResponse> regResponseGenericResponse) {
        loadingDialog.dismiss();
        if (regResponseGenericResponse.isSuccessful()) {
            SharedPrefUtils.putStringSharedPrefs(this, regResponseGenericResponse.getResponse().getKey(), Info.TOKEN);
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        } else
            MvvmUtils.printErrors(this, regResponseGenericResponse);
    }
}