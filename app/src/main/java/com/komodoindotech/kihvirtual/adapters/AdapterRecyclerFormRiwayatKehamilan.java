package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;

import java.util.List;

public class AdapterRecyclerFormRiwayatKehamilan extends RecyclerView.Adapter<AdapterRecyclerFormRiwayatKehamilan.viewHolder> {

    public static interface onCheckedChangeListener{
        void change(Boolean isChecked);
    }

    Context mContext;
    List<RiwayatKehamilan> pilihanObjects;
    private onCheckedChangeListener listener;

    public AdapterRecyclerFormRiwayatKehamilan(Context mContext, List<RiwayatKehamilan> pilihanObjects) {
        this.mContext = mContext;
        this.pilihanObjects = pilihanObjects;
    }

    public AdapterRecyclerFormRiwayatKehamilan(Context mContext, List<RiwayatKehamilan> pilihanObjects, onCheckedChangeListener listener) {
        this.mContext = mContext;
        this.pilihanObjects = pilihanObjects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.component_card_pilihan_form, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(getItemViewType(position) == 1){
            holder.bind(mContext, pilihanObjects.get(position), position, listener);
        } else {
            holder.bindLabel(mContext, pilihanObjects.get(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return pilihanObjects.get(position).getDisabled() ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return pilihanObjects.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView label, numbering;
        CheckBox aSwitch;
        CardView container;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.pilihan_label);
            numbering = itemView.findViewById(R.id.pilihan_number);
            aSwitch = itemView.findViewById(R.id.pilihan_switch);
            container = itemView.findViewById(R.id.pilihan_container);

        }

        public void bind(Context mContext, RiwayatKehamilan pilihanObject, int position, onCheckedChangeListener listener) {
            label.setText(Html.fromHtml(pilihanObject.getLabel()));
            numbering.setText(position + ". ");
            aSwitch.setChecked(pilihanObject.value == null ? false : pilihanObject.value );
            aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                pilihanObject.value = isChecked;
                listener.change(isChecked);
            });
        }

        public void bindLabel(Context mContext, RiwayatKehamilan pilihanObject, int position) {
            label.setText(pilihanObject.getLabel());
            aSwitch.setVisibility(View.GONE);
        }
    }

}
