package com.komodoindotech.kihvirtual.ui.data;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.komodoindotech.kihvirtual.KesimpulanActivity;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerDataPendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;

import java.util.List;

public class DaftarDataFragment extends Fragment {

    public static DaftarDataFragment newInstance() {
        return new DaftarDataFragment();
    }
    private MaterialToolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daftar_data_fragment, container, false);
        DaftarDataViewModel mViewModel = new ViewModelProvider(requireActivity()).get(DaftarDataViewModel.class);


        List<PendaftaranDanRiwayat> pendaftaranDanRiwayatList;

        pendaftaranDanRiwayatList = mViewModel.pendaftaranDanRiwayatLiveData.getValue();
        AdapterRecyclerDataPendaftaran adapterRecyclerDataPendaftaran = new AdapterRecyclerDataPendaftaran(requireContext(), pendaftaranDanRiwayatList, getChildFragmentManager(), pendaftaranDanRiwayat -> {
            Intent intent = new Intent(requireContext(), KesimpulanActivity.class);
            intent.putExtra("id_pendaftaran_cloud", pendaftaranDanRiwayat.pendaftaran.getUid());
            startActivity(intent);
        });

        toolbar = root.findViewById(R.id.toolbar);
        initToolbar();


        RecyclerView recyclerView = root.findViewById(R.id.daftar_data_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRecyclerDataPendaftaran);

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}