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
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerReviewKehamilan extends RecyclerView.Adapter<AdapterRecyclerReviewKehamilan.viewHolder> {

    private Context context;
    private List<RiwayatKehamilan> riwayatKehamilanList;

    public AdapterRecyclerReviewKehamilan(Context context, List<RiwayatContract> riwayatContracts) {
        this.context = context;
        List<RiwayatKehamilan> dipilih = new ArrayList<>();
        for(RiwayatContract value : riwayatContracts){
            RiwayatKehamilan riwayatKehamilan = (RiwayatKehamilan) value;
            if(riwayatKehamilan.value)
                dipilih.add(riwayatKehamilan);
        }
        this.riwayatKehamilanList = dipilih;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_riwayat_review_kehamilan, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(riwayatKehamilanList.get(position));
    }

    @Override
    public int getItemCount() {
        return riwayatKehamilanList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView label;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
        }

        public void bind(RiwayatKehamilan riwayatKehamilan) {
            label.setText(Html.fromHtml(riwayatKehamilan.getLabel()));
        }
    }
}
