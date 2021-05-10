package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.PilihanObject;

import java.util.List;

public class AdapterRecyclerFormRiwayatPersalinan extends RecyclerView.Adapter<AdapterRecyclerFormRiwayatPersalinan.viewHolder> {

    Context mContext;
    List<PilihanObject> pilihanObjects;

    public AdapterRecyclerFormRiwayatPersalinan(Context mContext, List<PilihanObject> pilihanObjects) {
        this.mContext = mContext;
        this.pilihanObjects = pilihanObjects;
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
            holder.bind(mContext, pilihanObjects.get(position), position);
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

        public void bind(Context mContext, PilihanObject pilihanObject, int position) {
            label.setText(Html.fromHtml(pilihanObject.getLabel()));
            numbering.setText(position + ". ");
        }

        public void bindLabel(Context mContext, PilihanObject pilihanObject, int position) {
            label.setText(pilihanObject.getLabel());
            aSwitch.setVisibility(View.GONE);
        }
    }
}
