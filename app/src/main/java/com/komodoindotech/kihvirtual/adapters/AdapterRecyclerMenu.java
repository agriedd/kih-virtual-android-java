package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.MenuObject;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterRecyclerMenu extends RecyclerView.Adapter<AdapterRecyclerMenu.viewHolder> {

    public interface onClickListener{
        void onClick(View v, Integer i);
    }

    private Context context;
    private List<MenuObject> menuObjectList;
    private onClickListener listener;


    public AdapterRecyclerMenu(Context context, List<MenuObject> menuObjectList, onClickListener listener) {
        this.context = context;
        this.menuObjectList = menuObjectList;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_menu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.bind(listener, menuObjectList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuObjectList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public TextView label, description;
        public CardView container;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            description = itemView.findViewById(R.id.description);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(onClickListener listener, MenuObject menuObject) {
            label.setText(menuObject.label);
            description.setText(menuObject.description);
            container.setOnClickListener(v -> {
                listener.onClick(v, menuObject.id);
            });
        }
    }
}
