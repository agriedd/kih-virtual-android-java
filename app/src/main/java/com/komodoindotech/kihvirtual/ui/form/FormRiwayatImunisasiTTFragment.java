package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerRiwayatImunisasiTTR;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatImunisasiTTFragment extends Fragment {

    private RecyclerView formRiwayatImunisasiTTView;
    private MaterialToolbar toolbar;
    private PendaftaranViewModel pendaftaranViewModel;
    private List<RiwayatImunisasi> tanggalObjects;

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

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        toolbar = root.findViewById(R.id.toolbar_riwayat_imuntt);
        formRiwayatImunisasiTTView = root.findViewById(R.id.list_riwayat_imunisasi_tt);

        initToolbar();

        initRiwayatImunisasiRecycler();

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> pendaftaranViewModel.previousPageForm());
    }

    private void initRiwayatImunisasiRecycler() {

        tanggalObjects = pendaftaranViewModel.getRiwayatImunisasiObjectLiveData().getValue();

        AdapterRecyclerRiwayatImunisasiTTR adapterRecyclerRiwayatImunisasiTTR = new AdapterRecyclerRiwayatImunisasiTTR(getContext(), tanggalObjects, listener);

        formRiwayatImunisasiTTView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        formRiwayatImunisasiTTView.setAdapter(adapterRecyclerRiwayatImunisasiTTR);
    }

    private final AdapterRecyclerRiwayatImunisasiTTR.onSetDate listener = date -> pendaftaranViewModel.setRiwayatImunisasiObjectLiveData(tanggalObjects);
}