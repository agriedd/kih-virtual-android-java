package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;

public class InformasiFormRiwayatPersalinanFragment extends Fragment {

    public InformasiFormRiwayatPersalinanFragment() {
    }

    public static InformasiFormRiwayatPersalinanFragment newInstance() {
        return new InformasiFormRiwayatPersalinanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_informasi_form_riwayat_persalinan, container, false);
        return root;
    }
}