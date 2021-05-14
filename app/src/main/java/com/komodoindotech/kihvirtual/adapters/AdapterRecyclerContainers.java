package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.reviews.AdapterRecyclerReviewImunisasi;
import com.komodoindotech.kihvirtual.adapters.reviews.AdapterRecyclerReviewKehamilan;
import com.komodoindotech.kihvirtual.adapters.reviews.AdapterRecyclerReviewKeluhan;
import com.komodoindotech.kihvirtual.adapters.reviews.AdapterRecyclerReviewPersalinan;
import com.komodoindotech.kihvirtual.models.RiwayatGroup;
import com.komodoindotech.kihvirtual.ui.review.pendaftaran.ReviewRiwayatKehamilan;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterRecyclerContainers extends RecyclerView.Adapter<AdapterRecyclerContainers.viewHolder> {

    private final Context context;
    private final List<RiwayatGroup> riwayatGroups;

    public AdapterRecyclerContainers(Context context, List<RiwayatGroup> riwayatGroups) {
        this.context = context;
        this.riwayatGroups = riwayatGroups;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_riwayat_grouping, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        RiwayatGroup riwayatGroup = riwayatGroups.get(position);
        holder.bind(context, riwayatGroup);
        if(riwayatGroup.riwayatContracts.size() > 0){
            if(riwayatGroup.uid.equals(RiwayatGroup.ID_KEHAMILAN)){
                holder.bindRecycler(riwayatGroup, new AdapterRecyclerReviewKehamilan(context, riwayatGroup.riwayatContracts));
            } else if(riwayatGroup.uid.equals(RiwayatGroup.ID_PERSALINAN)){
                holder.bindRecycler(riwayatGroup, new AdapterRecyclerReviewPersalinan(context, riwayatGroup.riwayatContracts));
            } else if(riwayatGroup.uid.equals(RiwayatGroup.ID_IMUNISASI)){
                holder.bindRecycler(riwayatGroup, new AdapterRecyclerReviewImunisasi(context, riwayatGroup.riwayatContracts));
            } else if(riwayatGroup.uid.equals(RiwayatGroup.ID_KELUHAN)){
                holder.bindRecycler(riwayatGroup, new AdapterRecyclerReviewKeluhan(context, riwayatGroup.riwayatContracts));
            }
        }
    }

    @Override
    public int getItemCount() {
        return riwayatGroups.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView title, subtitle;
        public RecyclerView recycler_childs;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            recycler_childs = itemView.findViewById(R.id.recycler_childs);
            
        }

        public void bind(Context context, RiwayatGroup riwayatGroup) {
            title.setText(riwayatGroup.getLabel());
            if(riwayatGroup.subheading != null){
                subtitle.setText(riwayatGroup.subheading);
            }
            recycler_childs.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            recycler_childs.setHasFixedSize(true);
            recycler_childs.setNestedScrollingEnabled(false);
        }

        public void bindRecycler(RiwayatGroup riwayatGroup, RecyclerView.Adapter adapter) {
            recycler_childs.setAdapter(adapter);
        }
    }

}
