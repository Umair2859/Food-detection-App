package com.app.fooddetection.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fooddetection.R;
import com.app.fooddetection.adapters.TypeRecyclerViewAdapter;
import com.app.fooddetection.info.Info;
import com.app.fooddetection.mvvm.MvvmUtils;
import com.app.fooddetection.mvvm.mapping_utils.GenericResponse;
import com.app.fooddetection.mvvm.pojos.Super;
import com.app.fooddetection.mvvm.pojos.response.CaptureResponse;
import com.app.fooddetection.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class CaptureHistoryActivity extends AppCompatActivity {

    List<Super> listInstances;
    Dialog loadingDialog;
    TypeRecyclerViewAdapter typeRecyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_history);

        recyclerView = findViewById(R.id.rv);
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);

        initCaptures();

    }

    private void initCaptures() {
        listInstances = new ArrayList<>();
        typeRecyclerViewAdapter = new TypeRecyclerViewAdapter(this, listInstances, Info.RV_TYPE_CAPTURE);
        recyclerView.setAdapter(typeRecyclerViewAdapter);
        loadingDialog.show();
        MvvmUtils.getViewModelRepo(this)
                .getCaptures(this)
                .observe(this, this::initImageResp);

    }

    private void initImageResp(GenericResponse<List<CaptureResponse>> listGenericResponse) {
        loadingDialog.dismiss();
        if (listGenericResponse.isSuccessful())
            initRv(listGenericResponse.getResponse());
        else
            MvvmUtils.printGeneralErrors(this, listGenericResponse.getErrorMessages());

    }

    private void initRv(List<CaptureResponse> response) {
        listInstances.clear();
        listInstances.addAll(response);
        typeRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void back(View view) {
        finish();
    }
}