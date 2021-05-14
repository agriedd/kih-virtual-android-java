package com.komodoindotech.kihvirtual.adapters.reviews;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.RiwayatContract;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerReviewKeluhan extends RecyclerView.Adapter<AdapterRecyclerReviewKeluhan.viewHolder> {

    private final Context context;
    private final List<RiwayatKeluhan> riwayatKeluhanList;

    public AdapterRecyclerReviewKeluhan(Context context, List<RiwayatContract> riwayatContracts) {
        this.context = context;
        List<RiwayatKeluhan> dipilih = new ArrayList<>();
        for(RiwayatContract value : riwayatContracts){
            RiwayatKeluhan riwayatKeluhan = (RiwayatKeluhan) value;
            if(riwayatKeluhan.value)
                dipilih.add(riwayatKeluhan);
        }
        this.riwayatKeluhanList = dipilih;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_riwayat_review_keluhan, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(riwayatKeluhanList.get(position));
    }

    @Override
    public int getItemCount() {
        return riwayatKeluhanList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView label;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
        }

        public void bind(RiwayatKeluhan riwayatKeluhan) {
            label.setText(Html.fromHtml(riwayatKeluhan.getLabel()));
        }
    }
}
