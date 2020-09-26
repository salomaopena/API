package com.fenixinnovation.api.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fenixinnovation.api.R;
import com.fenixinnovation.api.ui.objects.Case;

import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.CaseViewHolder> {

    private final Context mContext;
    private List<Case> casesModelList;

    public CaseAdapter(Context mContext, List<Case> casesModelList) {
        this.mContext = mContext;
        this.casesModelList = casesModelList;
    }


    @NonNull
    @Override
    public CaseAdapter.CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new CaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseAdapter.CaseViewHolder holder, int position) {
        try {
            Case model = casesModelList.get(position);

            holder.name.setText(model.getName() + " "+model.getNickname());
            holder.birthday.setText(model.getBirthday());
            holder.city.setText(model.getCity());
            holder.province.setText(model.getProvince());
            holder.status.setText(model.getStatus());
            holder.createdAt.setText(model.getCreatedAt());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR : ", "Message : " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return casesModelList.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView birthday;
        private TextView city;
        private TextView province;
        private TextView status;
        private TextView createdAt;

        public CaseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_patient_name);
            birthday = itemView.findViewById(R.id.tv_patient_birthday);
            city = itemView.findViewById(R.id.tv_patient_city);
            province = itemView.findViewById(R.id.tv_patient_province);
            status = itemView.findViewById(R.id.tv_patient_status);
            createdAt = itemView.findViewById(R.id.tv_patient_created_at);
        }
    }
}
