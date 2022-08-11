package com.app.fooddetection.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fooddetection.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class TypeRecyclerViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView ivUserProfile;

    TextView tvTitle;
    TextView tvSuggestion;
    TextView tvCalories;

    TextView tvNoFoodItems;
    TextView tvTotalConsumed;
    TextView tvDate;

    public TypeRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        ivUserProfile = itemView.findViewById(R.id.iv_preview);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvSuggestion = itemView.findViewById(R.id.tv_suggestion);
        tvCalories = itemView.findViewById(R.id.tv_cal);
        tvNoFoodItems = itemView.findViewById(R.id.tv_quantity);
        tvTotalConsumed = itemView.findViewById(R.id.tv_consumed);
        tvDate = itemView.findViewById(R.id.tv_date);

    }

}


