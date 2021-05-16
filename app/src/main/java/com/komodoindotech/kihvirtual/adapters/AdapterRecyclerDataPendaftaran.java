package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.ui.review.InfoDataFragment;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterRecyclerDataPendaftaran extends RecyclerView.Adapter<AdapterRecyclerDataPendaftaran.viewHolder> {

    public static interface onClickListener{
        void onClick(PendaftaranDanRiwayat pendaftaranDanRiwayat);
    }

    Context context;
    List<PendaftaranDanRiwayat> pendaftaranDanRiwayatList;
    FragmentManager fragmentManager;
    onClickListener listener;


    public AdapterRecyclerDataPendaftaran(Context context, List<PendaftaranDanRiwayat> pendaftaranDanRiwayatList) {
        this.context = context;
        this.pendaftaranDanRiwayatList = pendaftaranDanRiwayatList;
    }

    public AdapterRecyclerDataPendaftaran(Context context, List<PendaftaranDanRiwayat> pendaftaranDanRiwayatList, FragmentManager fragmentManager) {
        this.context = context;
        this.pendaftaranDanRiwayatList = pendaftaranDanRiwayatList;
        this.fragmentManager = fragmentManager;
    }

    public AdapterRecyclerDataPendaftaran(Context context, List<PendaftaranDanRiwayat> pendaftaranDanRiwayatList, FragmentManager fragmentManager, onClickListener listener) {
        this.context = context;
        this.pendaftaranDanRiwayatList = pendaftaranDanRiwayatList;
        this.fragmentManager = fragmentManager;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_data_pendaftaran, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(context, pendaftaranDanRiwayatList.get(position), fragmentManager, listener);
    }

    @Override
    public int getItemCount() {
        return pendaftaranDanRiwayatList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView label, description;
        CardView cardView;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.subtitle);
            cardView = itemView.findViewById(R.id.container);
        }

        public void bind(Context context, PendaftaranDanRiwayat pendaftaranDanRiwayat, FragmentManager fragmentManager, onClickListener listener) {
            String value;
            if(pendaftaranDanRiwayat.pendaftaran.getCreated_at() != null){
                value = "Data Ibu " + pendaftaranDanRiwayat.pendaftaran.getNama();
                description.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(
                        new Date(pendaftaranDanRiwayat.pendaftaran.getCreated_at())
                ));
            } else {
                value = "Data Ibu " + pendaftaranDanRiwayat.pendaftaran.getNama();
                description.setText("Tidak ada tanggal");
            }
            label.setText(value);
            cardView.setOnClickListener(v -> {
                listener.onClick(pendaftaranDanRiwayat);
            });
        }
    }
}
