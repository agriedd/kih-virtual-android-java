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
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerReviewPersalinan extends RecyclerView.Adapter<AdapterRecyclerReviewPersalinan.viewHolder> {

    private final Context context;
    private final List<RiwayatPersalinan> riwayatPersalinanList;

    public AdapterRecyclerReviewPersalinan(Context context, List<RiwayatContract> riwayatContracts) {
        this.context = context;
        List<RiwayatPersalinan> dipilih = new ArrayList<>();
        for(RiwayatContract value : riwayatContracts){
            RiwayatPersalinan riwayatPersalinan = (RiwayatPersalinan) value;
            if(riwayatPersalinan.value)
                dipilih.add(riwayatPersalinan);
        }
        this.riwayatPersalinanList = dipilih;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_riwayat_review_persalinan, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(riwayatPersalinanList.get(position));
    }

    @Override
    public int getItemCount() {
        return riwayatPersalinanList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView label;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
        }

        public void bind(RiwayatPersalinan riwayatPersalinan) {
            label.setText(Html.fromHtml(riwayatPersalinan.getLabel()));
        }
    }
}
