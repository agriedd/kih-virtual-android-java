package com.komodoindotech.kihvirtual.ui.kelas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.KelasObject;

public class ComponentKelas extends Fragment {

    private KelasObject kelasObject;
    public TextView name, description;
    public CardView containerView;
    public AppCompatImageView cover;

    public ComponentKelas(KelasObject kelasObject) {
        this.kelasObject = kelasObject;
    }

    public static ComponentKelas newInstance(KelasObject kelasObject) {
        ComponentKelas fragment = new ComponentKelas(kelasObject);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.component_card_kelas, container, false);

        name = root.findViewById(R.id.kelas_name);
        description = root.findViewById(R.id.kelas_description);
        containerView = root.findViewById(R.id.container);
        cover = root.findViewById(R.id.kelas_cover);

        initView();

        return root;
    }

    private void initView() {
        name.setText(kelasObject.getName());
        description.setText(kelasObject.getDescription());
        try {
            if(kelasObject.getImage() != null && kelasObject.getImage().length() > 0){
                cover.clearColorFilter();
                Glide.with(requireContext())
                        .load(kelasObject.getImage())
                        .centerCrop()
                        .into(cover);
            }
        } catch (Exception e){
            Log.d("imagekelas", "bind: " + e.getMessage());
        }
        containerView.setOnClickListener(v -> {
            String url = kelasObject.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }
}