package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormRiwayatPersalinan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.util.List;

public class FormRiwayatPersalinanRecyclerFragment extends Fragment {

    private RecyclerView recyclerViewFormRiwayatPersalinan;
    private List<RiwayatPersalinan> pilihanObjects;
    private PendaftaranViewModel pendaftaranViewModel;

    public FormRiwayatPersalinanRecyclerFragment() {
    }

    public static FormRiwayatPersalinanRecyclerFragment newInstance() {
        return new FormRiwayatPersalinanRecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_riwayat_persalinan_recycler, container, false);

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        recyclerViewFormRiwayatPersalinan = root.findViewById(R.id.recycler_form_riwayat_persalinan);

        initRecycler();

        return root;
    }

    private void initRecycler() {
        pilihanObjects = pendaftaranViewModel.getRiwayatPersalinanObjectLiveData().getValue();
        AdapterRecyclerFormRiwayatPersalinan adapterRecyclerFormRiwayatPersalinan = new AdapterRecyclerFormRiwayatPersalinan(getContext(), pilihanObjects, listener);
        recyclerViewFormRiwayatPersalinan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFormRiwayatPersalinan.setAdapter(adapterRecyclerFormRiwayatPersalinan);
    }

    private final AdapterRecyclerFormRiwayatPersalinan.onCheckedChangeListener listener = status -> pendaftaranViewModel.setRiwayatPersalinanObjectLiveData(pilihanObjects);
}