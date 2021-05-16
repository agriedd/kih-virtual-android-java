package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterRecyclerTelepon extends RecyclerView.Adapter<AdapterRecyclerTelepon.viewHolder> {

    private Context context;
    private List<Object> teleponList;

    public AdapterRecyclerTelepon(Context context, List<Object> teleponList) {
        this.context = context;
        this.teleponList = teleponList;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_telepon, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(teleponList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return teleponList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        public TextView telepon;
        public AppCompatImageView pesan;
        public CardView container;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            telepon = itemView.findViewById(R.id.telepon);
            pesan = itemView.findViewById(R.id.pesan);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Object o, Context context) {
            telepon.setText(o.toString().trim());
            container.setOnClickListener(v -> {
                Uri u = Uri.parse("tel:" + o.toString().trim());
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) context;
                    appCompatActivity.startActivity(i);
                }
                catch (SecurityException s){
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
            });

            pesan.setOnClickListener(v -> {
                String number = o.toString().trim();
                number = number.replaceAll("^(0|\\+62|62)(.*)$", "+62$2");
                Log.d("wtf", "bind: "+number);
                String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                AppCompatActivity appCompatActivity = (AppCompatActivity) context;
                appCompatActivity.startActivity(i);
            });
        }
    }
}
