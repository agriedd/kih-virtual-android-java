package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerRiwayatImunisasiTTR;
import com.komodoindotech.kihvirtual.json.PendaftaranObject;
import com.komodoindotech.kihvirtual.json.TanggalObject;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatImunisasiTTFragment extends Fragment {

    private View root;
    private RecyclerView formRiwayatImunisasiTTView;
    private List<TanggalObject> tanggalObjects;
    private AdapterRecyclerRiwayatImunisasiTTR adapterRecyclerRiwayatImunisasiTTR;

    public FormRiwayatImunisasiTTFragment() {}

    public static FormRiwayatImunisasiTTFragment newInstance() {
        FormRiwayatImunisasiTTFragment fragment = new FormRiwayatImunisasiTTFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_form_riwayat_imunisasi_t_t, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar_riwayat_imuntt);
        formRiwayatImunisasiTTView = root.findViewById(R.id.list_riwayat_imunisasi_tt);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);

        initRiwayatImunisasiRecycler();

        return root;
    }

    private void initRiwayatImunisasiRecycler() {

        tanggalObjects = new ArrayList<>();
        tanggalObjects.add(new TanggalObject("tt1", "TT1 tanggal"));
        tanggalObjects.add(new TanggalObject("tt2", "TT2 tanggal"));
        tanggalObjects.add(new TanggalObject("tt3", "TT3 tanggal"));
        tanggalObjects.add(new TanggalObject("tt4", "TT4 tanggal"));
        tanggalObjects.add(new TanggalObject("tt5", "TT5 tanggal"));

        adapterRecyclerRiwayatImunisasiTTR = new AdapterRecyclerRiwayatImunisasiTTR(getContext(), tanggalObjects);

        formRiwayatImunisasiTTView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        formRiwayatImunisasiTTView.setAdapter(adapterRecyclerRiwayatImunisasiTTR);
    }
}