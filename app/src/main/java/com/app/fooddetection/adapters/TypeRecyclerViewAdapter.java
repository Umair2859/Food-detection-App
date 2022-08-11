package com.app.fooddetection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fooddetection.R;
import com.app.fooddetection.info.Info;
import com.app.fooddetection.mvvm.pojos.Super;
import com.app.fooddetection.mvvm.pojos.response.CaptureResponse;
import com.app.fooddetection.mvvm.pojos.response.ConsumeResponse;
import com.app.fooddetection.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TypeRecyclerViewAdapter extends RecyclerView.Adapter<TypeRecyclerViewHolder> implements Info {

    Context context;
    List<Super> listInstances;
    int type;

    public TypeRecyclerViewAdapter(Context context, List<Super> listInstances, int type) {
        this.context = context;
        this.listInstances = listInstances;
        this.type = type;
    }

    @NonNull
    @Override
    public TypeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View view;
        if (type == RV_TYPE_CAPTURE)
            view = li.inflate(R.layout.capture_history, parent, false);
        else
            view = li.inflate(R.layout.consume_history, parent, false);

        return new TypeRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeRecyclerViewHolder holder, int position) {
        if (type == 1)
            initCapture(holder, position);
        else
            initConsume(holder, position);
    }

    private void initConsume(TypeRecyclerViewHolder holder, int position) {
        ConsumeResponse consumeResponse = (ConsumeResponse) listInstances.get(position);
        holder.tvNoFoodItems.setText(String.valueOf(consumeResponse.getQuantity()));
        holder.tvTitle.setText(consumeResponse.getFoodItem());
        String calories = consumeResponse.getCalories() + " calories";
        holder.tvCalories.setText(calories);
        holder.tvCalories.setVisibility(View.GONE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = format.parse(consumeResponse.getCreatedOn());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateString = format.format(new Date());
        holder.tvDate.setText(dateString);

        String totalCalories = String.valueOf(consumeResponse.getCalories());
        holder.tvTotalConsumed.setText(totalCalories);
    }

    private void initCapture(TypeRecyclerViewHolder holder, int position) {
        CaptureResponse captureResponse = (CaptureResponse) listInstances.get(position);
        holder.tvTitle.setText(captureResponse.getCategory());
        String cal = captureResponse.getCalories() + " calories";
        holder.tvCalories.setText(cal);
        holder.ivUserProfile.setImageURI(captureResponse.getImage());
        holder.tvSuggestion.setText(Utils.getSuggestion(captureResponse.getCalories(), Utils.userPojo.getBmi()));
    }

    @Override
    public int getItemCount() {
        return listInstances.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
