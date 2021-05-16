package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.PuskesmasObject;
import com.komodoindotech.kihvirtual.ui.puskesmas.MenuPuskesmasDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterRecyclerPuskesmas extends RecyclerView.Adapter<AdapterRecyclerPuskesmas.viewHolder> {

    Context context;
    List<PuskesmasObject> puskesmasObjectList;

    public AdapterRecyclerPuskesmas(Context context, List<PuskesmasObject> puskesmasObjectList) {
        this.context = context;
        this.puskesmasObjectList = puskesmasObjectList;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_puskesmas, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(puskesmasObjectList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return puskesmasObjectList.size();
    }

    public void update(List<PuskesmasObject> puskesmasObjectList) {
        this.puskesmasObjectList = puskesmasObjectList;
        this.notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public TextView nama, alamat;
        public AppCompatImageView toggler;
        public CardView container;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            toggler = itemView.findViewById(R.id.context_menu_toggler);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(PuskesmasObject puskesmasObject, Context context) {
            nama.setText(puskesmasObject.getNama());
            alamat.setText(puskesmasObject.getAlamat());
            container.setOnClickListener(v -> {
                MenuPuskesmasDialogFragment dialogFragment = new MenuPuskesmasDialogFragment(puskesmasObject);
                dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), "menu-puskesmas" );
            });
        }
    }
}
