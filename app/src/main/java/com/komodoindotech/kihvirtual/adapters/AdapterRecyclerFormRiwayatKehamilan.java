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
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;

import java.util.List;

public class AdapterRecyclerFormRiwayatKehamilan extends RecyclerView.Adapter<AdapterRecyclerFormRiwayatKehamilan.viewHolder> {

    public interface onCheckedChangeListener{
        void change(Boolean isChecked);
    }

    Context mContext;
    List<RiwayatKehamilan> pilihanObjects;
    private final onCheckedChangeListener listener;


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
            holder.bind(pilihanObjects.get(position), position, listener);
        } else {
            holder.bindLabel(pilihanObjects.get(position));
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

    @Override
    public void onViewAttachedToWindow(@NonNull viewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.aSwitch.setChecked(
                pilihanObjects.get(
                        holder.getAdapterPosition()
                ).value
        );
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView label, numbering;
        public CheckBox aSwitch;
        public CardView container;
        public Boolean checked;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.pilihan_label);
            numbering = itemView.findViewById(R.id.pilihan_number);
            aSwitch = itemView.findViewById(R.id.pilihan_switch);
            container = itemView.findViewById(R.id.pilihan_container);

        }

        public void bind(RiwayatKehamilan pilihanObject, int position, onCheckedChangeListener listener) {
            label.setText(Html.fromHtml(pilihanObject.getLabel()));
            String value = position + ". ";
            numbering.setText(value);
            if(checked == null){
                checked = pilihanObject.value != null ? pilihanObject.value : false;
            }
            aSwitch.setChecked(checked);
            aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                pilihanObject.value = isChecked;
                checked = isChecked;
                listener.change(isChecked);
            });
        }

        public void bindLabel(RiwayatKehamilan pilihanObject) {
            label.setText(pilihanObject.getLabel());
            aSwitch.setVisibility(View.GONE);
        }
    }

}
