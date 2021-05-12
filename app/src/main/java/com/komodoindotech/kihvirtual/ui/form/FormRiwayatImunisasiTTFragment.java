package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerRiwayatImunisasiTTR;
import com.komodoindotech.kihvirtual.json.TanggalObject;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatImunisasiTTFragment extends Fragment {

    private RecyclerView formRiwayatImunisasiTTView;
    private MaterialToolbar toolbar;

    public FormRiwayatImunisasiTTFragment() {}

    public static FormRiwayatImunisasiTTFragment newInstance() {
        return new FormRiwayatImunisasiTTFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_riwayat_imunisasi_t_t, container, false);

        toolbar = root.findViewById(R.id.toolbar_riwayat_imuntt);
        formRiwayatImunisasiTTView = root.findViewById(R.id.list_riwayat_imunisasi_tt);

        initToolbar();

        initRiwayatImunisasiRecycler();

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void initRiwayatImunisasiRecycler() {

        List<TanggalObject> tanggalObjects = new ArrayList<>();
        tanggalObjects.add(new TanggalObject("tt1", "TT1 tanggal"));
        tanggalObjects.add(new TanggalObject("tt2", "TT2 tanggal"));
        tanggalObjects.add(new TanggalObject("tt3", "TT3 tanggal"));
        tanggalObjects.add(new TanggalObject("tt4", "TT4 tanggal"));
        tanggalObjects.add(new TanggalObject("tt5", "TT5 tanggal"));

        AdapterRecyclerRiwayatImunisasiTTR adapterRecyclerRiwayatImunisasiTTR = new AdapterRecyclerRiwayatImunisasiTTR(getContext(), tanggalObjects);

        formRiwayatImunisasiTTView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        formRiwayatImunisasiTTView.setAdapter(adapterRecyclerRiwayatImunisasiTTR);
    }
}