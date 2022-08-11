package com.app.fooddetection.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fooddetection.R;
import com.app.fooddetection.mvvm.MvvmUtils;
import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.request.PostRegPojo;
import com.app.fooddetection.mvvm.pojos.response.RegResponse;
import com.app.fooddetection.mvvm.pojos.response.UserPojo;
import com.app.fooddetection.utils.DialogUtils;

public class BMICalculationActivity extends AppCompatActivity {

    EditText etHeight;
    EditText etWeight;
    EditText etBMI;

    double dblEtHeight;
    int dblEtWeight;
    double dblEtBMI;

    Button btnSignUp;
    Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);
        initViews();
        initTextWatcher();
        btnSignUp.setOnClickListener(view -> signUp());
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);

    }


    private void initTextWatcher() {
        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                castStrings();
                calculateBmi();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void calculateBmi() {
        try {
            double weight = Double.parseDouble(etWeight.getText().toString());
            double height = Double.parseDouble(etHeight.getText().toString()) / 0.032808;
            double bmi = (weight / (height * height)) * 10000;
            etBMI.setText(String.valueOf(bmi));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void castStrings() {
        try {
            dblEtHeight = Double.parseDouble(etHeight.getText().toString());
            dblEtWeight = Integer.parseInt(etWeight.getText().toString());
            dblEtBMI = Double.parseDouble(etBMI.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etBMI = findViewById(R.id.et_phone);
        btnSignUp = findViewById(R.id.bmi);
    }

    public void back(View view) {
        finish();
    }

    private void signUp() {
        Log.i("TAG", "signUp: ");
        castStrings();
        SignUpActivity.userPojo.setBmi(dblEtBMI);
        SignUpActivity.userPojo.setHeight(dblEtHeight);
        SignUpActivity.userPojo.setWeight(dblEtWeight);
        UserPojo u = SignUpActivity.userPojo;
        loadingDialog.show();
        MvvmUtils.getViewModelRepo(this).postAuth(new PostRegPojo(
                u.getUsername() + "first",
                u.getUsername() + "last",
                u.getUsername(),
                u.getEmail(),
                SignUpActivity.strEtPassword,
                SignUpActivity.strEtPassword,
                u.getBmi(),
                u.getHeight(),
                u.getWeight()

        )).observe(this, this::initResponse);
    }

    private void initResponse(GenericResponse<RegResponse> regResponseGenericResponse) {
        loadingDialog.dismiss();
        if (regResponseGenericResponse.isSuccessful()) {
            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
            finish();
            SignUpActivity.context.finish();
        } else {
            MvvmUtils.printErrors(this, regResponseGenericResponse);
        }
    }

}