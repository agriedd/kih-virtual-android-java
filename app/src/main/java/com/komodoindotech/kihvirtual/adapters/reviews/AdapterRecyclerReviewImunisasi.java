package com.komodoindotech.kihvirtual.adapters.reviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.RiwayatContract;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerReviewImunisasi extends RecyclerView.Adapter<AdapterRecyclerReviewImunisasi.viewHolder> {

    private Context context;
    private List<RiwayatImunisasi> riwayatImunisasiList;

    public AdapterRecyclerReviewImunisasi(Context context, List<RiwayatContract> riwayatContracts) {
        this.context = context;
        List<RiwayatImunisasi> dipilih = new ArrayList<>();
        for(RiwayatContract value : riwayatContracts){
            RiwayatImunisasi riwayatImunisasi = (RiwayatImunisasi) value;
            if(riwayatImunisasi.value != null && riwayatImunisasi.value.trim().length() > 0)
                dipilih.add(riwayatImunisasi);
        }
        this.riwayatImunisasiList = dipilih;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_riwayat_review_imunisasi, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(riwayatImunisasiList.get(position));
    }

    @Override
    public int getItemCount() {
        return riwayatImunisasiList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView label, value;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            value = itemView.findViewById(R.id.value);
        }

        public void bind(RiwayatImunisasi riwayatImunisasi) {
            label.setText(riwayatImunisasi.getLabel());
            value.setText(riwayatImunisasi.value);
        }
    }
}
