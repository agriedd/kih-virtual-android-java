package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;

import java.util.List;

public class AdapterRecyclerFormKeluhan extends RecyclerView.Adapter<AdapterRecyclerFormKeluhan.viewHolder> {

    public interface onCheckedChangeListener{
        void onChange(Boolean status, Integer position);
    }

    private final Context mContext;
    private final List<RiwayatKeluhan> pilihanObjects;
    private final onCheckedChangeListener listener;

    public AdapterRecyclerFormKeluhan(Context mContext, List<RiwayatKeluhan> pilihanObjects, onCheckedChangeListener listener) {
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
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        if(getItemViewType(position) == 1){
            holder.bind(pilihanObjects.get(position), position, listener);
        } else {
            holder.bindLabel(pilihanObjects.get(position));
        }
    }

    @Override
    public void onViewRecycled(@NonNull viewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull viewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull viewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.aSwitch.setChecked(
                pilihanObjects.get(
                        holder.getAdapterPosition()
                ).value
        );
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull viewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return pilihanObjects.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return pilihanObjects.get(position).getDisabled() ? 0 : 1;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView label, numbering;
        public MaterialCheckBox aSwitch;
        public CardView container;
        public Boolean checked;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.pilihan_label);
            numbering = itemView.findViewById(R.id.pilihan_number);
            aSwitch = itemView.findViewById(R.id.pilihan_switch);
            container = itemView.findViewById(R.id.pilihan_container);
        }

        public void bind(RiwayatKeluhan pilihanObject, int position, onCheckedChangeListener listener) {
            label.setText(Html.fromHtml(pilihanObject.getLabel()));
            String value = position + ". ";
            numbering.setText(value);
//
            if(checked == null){
                checked = pilihanObject.value != null ? pilihanObject.value : false;
            }
            aSwitch.setOnCheckedChangeListener(null);

            aSwitch.setChecked(checked);

            aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                pilihanObject.value = isChecked;
                checked = isChecked;
                listener.onChange(isChecked, position);
            });
        }

        public void bindLabel(RiwayatKeluhan pilihanObject) {
            label.setText(pilihanObject.getLabel());
            aSwitch.setVisibility(View.GONE);
        }
    }
}
