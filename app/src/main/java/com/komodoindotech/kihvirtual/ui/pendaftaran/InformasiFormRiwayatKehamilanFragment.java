package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.komodoindotech.kihvirtual.R;

public class InformasiFormRiwayatKehamilanFragment extends Fragment {

    public InformasiFormRiwayatKehamilanFragment() {

    }

    public static InformasiFormRiwayatKehamilanFragment newInstance() {
        return new InformasiFormRiwayatKehamilanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_informasi_form_riwayat_kehamilan, container, false);
        ExtendedFloatingActionButton extendedFloatingActionButton = root.findViewById(R.id.btn_lewati);
        extendedFloatingActionButton.setOnClickListener(v -> {
//            startActivity(new Intent());
        });
        return root;
    }
}